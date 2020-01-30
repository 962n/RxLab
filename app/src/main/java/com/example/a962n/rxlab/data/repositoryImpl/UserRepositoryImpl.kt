package com.example.a962n.rxlab.data.repositoryImpl

import com.example.a962n.rxlab.data.AppDatabase
import com.example.a962n.rxlab.data.UserEntity
import com.example.a962n.rxlab.domain.UserRepository
import kotlin.random.Random

class UserRepositoryImpl constructor(database: AppDatabase) : UserRepository {
    private val userDao = database.userDao()

    override fun loadUsers() {




    }

    private fun callDummyApi(): List<UserEntity> {
        return (1..20).map { createRandomUser() }
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