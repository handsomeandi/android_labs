package com.example.androidlabs

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidlabs.database.CityDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "city-database"


class CityRepository private constructor(context: Context) {

    private val database : CityDatabase = Room.databaseBuilder(
        context.applicationContext,
        CityDatabase::class.java,
        DATABASE_NAME
    ).addCallback(object: RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Executors.newSingleThreadScheduledExecutor().execute {
                populateData()
            }
        }
    }).build().apply {

    }

    private val cityDao = database.cityDao()
    private val executor = Executors.newSingleThreadExecutor() //remove before pushing

    fun getcitys(): LiveData<List<City>> = cityDao.getCitys()



    fun populateData() {
        database.cityDao().addCities(listOf(
            City(title = "Simferopol", date = Calendar.getInstance().time, isFavorite = true, img = R.drawable.ic_city),
            City(title = "Krasnodar", date = Calendar.getInstance().time, isFavorite = true, img = R.drawable.ic_sun),
            City(title = "Sochi", date = Calendar.getInstance().time, isFavorite = true, img = R.drawable.ic_house),
            City(title = "Kerch", date = Calendar.getInstance().time, isFavorite = false, img = R.drawable.ic_city),
            City(title = "Yalta", date = Calendar.getInstance().time, isFavorite = false, img = R.drawable.ic_house),
        ))
    }
    companion object {
        private var INSTANCE: CityRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CityRepository(context)
            }
        }

        fun get(): CityRepository {
            return INSTANCE ?:
            throw IllegalStateException("cityRepository must be initialized")
        }
    }
}