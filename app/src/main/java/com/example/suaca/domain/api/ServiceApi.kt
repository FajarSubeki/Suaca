package com.example.suaca.domain.api

import com.example.suaca.data.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("current.json")
    suspend fun getCurrentWeather(@Query("key") apiKey: String, @Query("q") city: String): Response<WeatherResponse>
}