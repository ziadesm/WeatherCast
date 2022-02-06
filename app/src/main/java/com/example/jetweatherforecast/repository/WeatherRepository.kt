package com.example.jetweatherforecast.repository
import android.util.Log
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.WeatherResponse
import com.example.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(query: String, units: String? = null, key: String? = null): DataOrException<WeatherResponse, Boolean, Exception> {
        val response = try {
            api.getWeather(query)

        } catch (e: Exception) {
            Log.e("ErrorWeather", "getWeather: >>> ${e.message}")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}