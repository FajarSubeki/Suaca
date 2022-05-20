package com.example.suaca.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.suaca.presentation.datasource.WeatherDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherDataSource: WeatherDataSource) : ViewModel(){

    fun getWeather(apiKey: String, city: String) = weatherDataSource.getCurrentWeather(apiKey, city)

}
