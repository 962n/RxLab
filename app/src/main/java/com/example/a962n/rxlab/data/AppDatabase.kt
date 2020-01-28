package com.example.a962n.rxlab.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}