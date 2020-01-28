package com.example.a962n.rxlab

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.a962n.rxlab.data.AppDatabase

class App : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sample.db"
        ).build()
    }
    val scheduler = AppSchedulerProvider()

    override fun onCreate() {
        super.onCreate()
    }
}

fun Context.app(): App = this as App