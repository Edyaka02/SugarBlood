package com.edmalyon.sugarblood

import android.app.Application
import androidx.room.Room
import com.edmalyon.sugarblood.data.local.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
//    override fun onCreate() {
//        super.onCreate()
//    }

//    val database by lazy {
//        Room.databaseBuilder(
//            this,
//            AppDatabase::class.java,
//            "db_sugarblood"
//        ).build()
//    }

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.iniciarBaseDeDatos(this)
    }
}