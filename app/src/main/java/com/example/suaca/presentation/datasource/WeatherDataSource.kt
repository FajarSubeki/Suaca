package com.example.suaca.presentation.datasource

import androidx.lifecycle.liveData
import com.example.resepku.data.base.Resource
import com.example.suaca.data.response.WeatherResponse
import com.example.suaca.domain.api.ServiceApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@ActivityRetainedScoped
class WeatherDataSource @Inject constructor(private val apiService: ServiceApi) {

    fun getCurrentWeather(key: String, city: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiService.getCurrentWeather(key, city)
            if (response.isSuccessful) {
                emit(Resource.success(response.body()?.current))
            }
        } catch (exception: Exception) {
            Resource.failed<WeatherResponse>(null, exception.message.toString())
        }
    }

}