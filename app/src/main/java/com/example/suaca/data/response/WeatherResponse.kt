package com.example.suaca.data.response

import com.example.suaca.data.model.Current
import com.example.suaca.data.model.Location
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current
)
