package com.example.jetweatherforecast.screens.main_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.WeatherResponse
import com.example.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(
    private val repo: WeatherRepository
): ViewModel() {
    suspend fun getWeather(city: String): DataOrException<WeatherResponse, Boolean, Exception> {
        return repo.getWeather(city)
    }
}