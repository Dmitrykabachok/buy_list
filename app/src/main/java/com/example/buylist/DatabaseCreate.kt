package com.example.buylist

import android.app.Application
import androidx.room.Room

class DatabaseCreate : Application(), DatabaseCreateProv {

    lateinit var db: AppDatabase

    override fun getDatabase(): AppDatabase {
        return db
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
