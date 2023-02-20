package com.example.weatherapp.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherData::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao():Dao

    companion object {
        fun getDbWeather(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "Weather"
            ).build()
        }


    }
}