package com.example.weatherapp.data.dataBase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CityWeatherData::class], version = 1)
abstract class MainDB : RoomDatabase() {


    abstract fun getDao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDbWeather(application: Application): MainDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    MainDB::class.java,
                    "Weather"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}