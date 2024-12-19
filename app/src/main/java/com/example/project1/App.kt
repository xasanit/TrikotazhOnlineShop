package com.example.project1

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class App: Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}