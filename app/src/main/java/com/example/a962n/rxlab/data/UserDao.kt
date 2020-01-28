package com.example.a962n.rxlab.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>
//    fun loadAllByIds(userIds: IntArray): List<UserEntity>
//    fun findByName(first: String, last: String): UserEntity
//    fun insertAll(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Update
    fun update(user: UserEntity)

}