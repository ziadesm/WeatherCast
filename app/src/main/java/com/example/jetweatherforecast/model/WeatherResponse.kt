package com.example.jetweatherforecast.model

import java.io.Serializable

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDayListModel>,
    val message: Double
): Serializable {
    data class City(
        val coord: Coord,
        val country: String,
        val id: Int,
        val name: String,
        val population: Int,
        val timezone: Int
    ): Serializable

    data class Coord(
        val lat: Double,
        val lon: Double
    ): Serializable

    data class FeelsLike(
        val day: Double,
        val eve: Double,
        val morn: Double,
        val night: Double
    ): Serializable

    data class WeatherModel(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    ): Serializable

    data class WeatherDayListModel(
        val clouds: Float,
        val deg: Float,
        val dt: UInt,
        val feels_like: FeelsLike,
        val gust: Double,
        val humidity: Float,
        val pop: Float,
        val pressure: Float,
        val rain: Double,
        val speed: Double,
        val sunrise: UInt,
        val sunset: UInt,
        val temp: Temp,
        val weather: List<WeatherModel>
    ): Serializable

    data class Temp(
        val day: Double,
        val eve: Double,
        val max: Double,
        val min: Double,
        val morn: Double,
        val night: Double
    ): Serializable
}