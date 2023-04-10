package com.example.androidlabs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidlabs.City

@Database(entities = [City::class], version = 1)
@TypeConverters(CityTypeConverters::class)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}