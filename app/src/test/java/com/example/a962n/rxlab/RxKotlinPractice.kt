package com.example.a962n.rxlab

import io.reactivex.Flowable
import io.reactivex.Scheduler
import org.junit.Test
import java.util.concurrent.TimeUnit

class RxKotlinPractice {

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


}