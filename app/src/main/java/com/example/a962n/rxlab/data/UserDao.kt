package com.example.a962n.rxlab.data

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT COUNT(*) FROM users")
    fun count() : Int

//    fun loadAllByIds(userIds: IntArray): List<UserEntity>
//    fun findByName(first: String, last: String): UserEntity
    @Insert
    fun insertAll(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Update
    fun update(user: UserEntity)

}