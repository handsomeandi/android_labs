package com.example.androidlabs

import androidx.lifecycle.ViewModel

class CityListViewModel : ViewModel() {


    private val cityRepository = CityRepository.get()
    val cityListLiveData = cityRepository.getcitys()
}