package com.example.a962n.rxlab.presentation.userEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a962n.rxlab.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class UserEditViewModel constructor(
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result


    class Factory(private val schedulerProvider: SchedulerProvider) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return UserEditViewModel(schedulerProvider) as T
        }
    }

    fun addRandomUser() {

    }

    fun addRandomUser10() {

    }

    fun deleteRandomUser() {

    }

    fun deleteAllUser() {

    }

}