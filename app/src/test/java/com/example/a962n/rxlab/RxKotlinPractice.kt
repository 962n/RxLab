package com.example.a962n.rxlab

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class RxKotlinPractice {

    @Test
    fun helloWorldByFlowable() {

        val flowable = Flowable
            .create(
                FlowableOnSubscribe<String> { emitter ->
                    println("subscribe thread =" + Thread.currentThread().name)
                    val data = listOf("Hello World", "こんにちは世界")
                    for (str in data) {
                        if (emitter.isCancelled) {
                            return@FlowableOnSubscribe
                        }
                        emitter.onNext(str)
                    }
                    emitter.onComplete()
                },
                BackpressureStrategy.BUFFER
            )
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())

        flowable
            .observeOn(Schedulers.computation())
            .subscribe(object : Subscriber<String> {

                private lateinit var subscription: Subscription

                override fun onComplete() {
                    println("onComplete thread =" + Thread.currentThread().name)
                }

                override fun onSubscribe(s: Subscription) {
                    subscription = s
                    subscription.request(1)
                }

                override fun onNext(t: String) {
                    println("onNext thread =" + Thread.currentThread().name)
                    println(t)
                    subscription.request(1)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }
            })
        flowable
            .observeOn(Schedulers.computation())
            .subscribe {
                println("onNext2 thread =" + Thread.currentThread().name)
            }

        Thread.sleep(500L)
    }

    @Test
    fun cancelSubscribe() {

        Flowable
            .interval(200L, TimeUnit.MILLISECONDS)
            .subscribe(object : Subscriber<Long> {
                private lateinit var subscription: Subscription
                private var startTime: Long by Delegates.notNull()
                override fun onComplete() {
                    println("onComplete thread =" + Thread.currentThread().name)
                }

                override fun onSubscribe(s: Subscription) {
                    startTime = System.currentTimeMillis()
                    subscription = s
                    subscription.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Long) {
                    println("onNext t = $t")
                    println("onNext thread =" + Thread.currentThread().name)
                    if (System.currentTimeMillis() - startTime > 500) {
                        println("subscription cancel")
                        subscription.cancel()
                    }
                }

                override fun onError(t: Throwable) {
                    println("onError thread =" + Thread.currentThread().name)
                }
            })

        Thread.sleep(5000L)
    }


    @Test
    fun merge() {
        val observable1 = Observable
            .interval(200,TimeUnit.MILLISECONDS)
            .map { "observable1 $it" }

        val observable2 = Observable
            .interval(200,TimeUnit.MILLISECONDS)
            .map { "observable2 $it" }

        val observableByMerge = observable1.mergeWith(observable2)
        observableByMerge.subscribe {
            println(it)
        }
        Thread.sleep(2000L)
    }

    @Test
    fun helloTestByObservable() {
        val observable: Observable<String> =
            Observable.create(ObservableOnSubscribe<String> { emitter ->
                println("subscribe thread =" + Thread.currentThread().name)
                val data = listOf("Hello World", "こんにちは世界")
                for (str in data) {
                    if (emitter.isDisposed) {
                        return@ObservableOnSubscribe
                    }
                    emitter.onNext(str)
                }
                emitter.onComplete()
            })


        observable
            .observeOn(Schedulers.computation())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    println("onComplete thread =" + Thread.currentThread().name)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    println("onNext thread =" + Thread.currentThread().name)
                    println(t)
                }

                override fun onError(e: Throwable) {
                    println("onError thread =" + Thread.currentThread().name)
                    e.printStackTrace()
                }
            })
        Thread.sleep(500)
    }

    @Test
    fun basic() {
        println("main")
        val fuga = Flowable.fromCallable {
            System.currentTimeMillis()
        }
        val flowable = Flowable
            .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter {
                println("filter")
                println("filter thread =" + Thread.currentThread().name)
                it % 2 == 0
            }
            .map {
                println("map")
                println("map thread =" + Thread.currentThread().name)
                it * 100
            }

        val disposable = flowable.subscribe {
            println("subscribe")
            println("subscribe thread =" + Thread.currentThread().name)
            println(it)
        }
        if (disposable.isDisposed) {
            println("dispose")
            disposable.dispose()
        }
    }

    private enum class SideEffectState {
        ADD,
        MULTIPLY;
    }

    private lateinit var sideEffectState: SideEffectState

    @Test
    fun `anti pattern by side effects`() {
        sideEffectState = SideEffectState.ADD

        val flowable = Flowable
            .interval(300L, TimeUnit.MILLISECONDS)
            .take(7)
            .scan { sum, data ->
                println("scan thread =" + Thread.currentThread().name)
                when (sideEffectState) {
                    SideEffectState.ADD -> sum + data
                    SideEffectState.MULTIPLY -> sum * data
                }
            }
        val disposable = flowable.subscribe {
            println("subscribe thread =" + Thread.currentThread().name)
            println(it)
        }
        Thread.sleep(1000L)
        sideEffectState = SideEffectState.MULTIPLY

        Thread.sleep(5000L)
        if (disposable.isDisposed) {
            disposable.dispose()
        }
    }

    @Test
    fun multiFlowable() {

    }


}