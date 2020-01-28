package com.example.a962n.rxlab.presentation.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a962n.rxlab.SchedulerProvider

class UserListViewModel constructor(
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    class Factory(private val schedulerProvider: SchedulerProvider) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(schedulerProvider) as T
        }
    }

}