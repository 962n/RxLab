package com.example.a962n.rxlab.presentation.userList

import android.util.Log
import androidx.lifecycle.*
import com.example.a962n.rxlab.SchedulerProvider
import com.example.a962n.rxlab.data.AppDatabase
import com.example.a962n.rxlab.data.UserDao
import com.example.a962n.rxlab.data.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

class UserListViewModel constructor(
    private val schedulerProvider: SchedulerProvider,
    private val userDao: UserDao
) : ViewModel() {

    //    private val _users: MutableLiveData<List<UserListAdapter.Item>> = MutableLiveData()
    private val _users: LiveData<List<UserListAdapter.Item>> =
        userDao
            .flowAll()
            .asLiveData()
            .map{ users ->
                Log.d("hoge","users size = ${users.size}")
                users.map { it.toUserListAdapterItem() }
            }

    val users get() = _users

    class Factory(
        private val schedulerProvider: SchedulerProvider,
        private val database: AppDatabase
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(schedulerProvider, database.userDao()) as T
        }
    }

    fun refresh() {
        if (true) {
            return
        }
//        val flow = userDao.flowAll().asLiveData()

//        Single.create<List<UserListAdapter.Item>>
//        {
//            val users = userDao.getAll()
//            it.onSuccess(users.map { it.toUserListAdapterItem() })
//        }
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//            .subscribe { success, error ->
//                _users.value = success
//            }


    }

}