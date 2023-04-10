package com.example.androidlabs.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidlabs.City
import java.util.*

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    fun getCitys(): LiveData<List<City>>

    @Insert
    fun addCities(cities: List<City>) //remove this before pushing
}