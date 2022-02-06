package com.example.jetweatherforecast.network

import com.example.jetweatherforecast.model.WeatherResponse
import com.example.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String? = "imperial",
        @Query("appid") api_key: String = Constants.API_KEY,
    ): WeatherResponse
}