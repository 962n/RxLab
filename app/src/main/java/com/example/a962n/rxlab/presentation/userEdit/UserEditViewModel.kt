package com.example.a962n.rxlab.presentation.userEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a962n.rxlab.SchedulerProvider
import com.example.a962n.rxlab.data.AppDatabase
import com.example.a962n.rxlab.data.UserEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlin.random.Random

class UserEditViewModel constructor(
    private val schedulerProvider: SchedulerProvider,
    private val database: AppDatabase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result


    class Factory(
        private val schedulerProvider: SchedulerProvider,
        private val database: AppDatabase
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return UserEditViewModel(schedulerProvider, database) as T
        }
    }

    fun addRandomUser() {
        val single = Single.create<String>
        {
            val userDao = database.userDao()
            val user = createRandomUser()
            userDao.insertAll(user)
            it.onSuccess("add random user success!!")
        }
        single
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe { t ->
                _result.value = t
            }.addTo(compositeDisposable)
    }

    fun addRandomUser10() {

    }

    fun deleteRandomUser() {

    }

    fun deleteAllUser() {

    }

    fun checkUserCount() {
        val observable = Observable.create<UserEntity>
        {
            val userDao = database.userDao()
            val users = userDao.getAll()
            users.forEach { user ->
                if (!it.isDisposed) {
                    it.onNext(user)
                    Thread.sleep(1000)
                }
            }
            it.onComplete()
        }
        observable
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe { t ->
                _result.value = t.toString()
            }.addTo(compositeDisposable)

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun createRandomUser(): UserEntity {
        val first = createRandomString(5)
        val last = createRandomString(8)
        return UserEntity(0, first, last)
    }

    private fun createRandomString(length: Int): String {
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { Random.nextInt(0, (charPool.size - 1)) }
            .map { charPool[it] }
            .joinToString("")
    }

}