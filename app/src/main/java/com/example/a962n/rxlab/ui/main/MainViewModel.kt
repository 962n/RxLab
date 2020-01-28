package com.example.a962n.rxlab.ui.main

import androidx.lifecycle.*
import com.example.a962n.rxlab.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class MainViewModel (schedulerProvider: SchedulerProvider): ViewModel() {

    class Factory(private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(schedulerProvider) as T
        }
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val timer = Flowable
        .interval(1000,TimeUnit.MILLISECONDS)
        .map { it.toInt() }
        .subscribeOn(schedulerProvider.computation())
        .observeOn(schedulerProvider.ui())
        .subscribe {
            long.value = it
        }.addTo(compositeDisposable)

    val long = MutableLiveData(0)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
