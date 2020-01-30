package com.example.a962n.rxlab.domain

import io.reactivex.Flowable

interface UserRepository {
    fun loadUsers()
}