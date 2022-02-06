package com.example.jetweatherforecast.navigation
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweatherforecast.screens.main_screen.MainScreen
import com.example.jetweatherforecast.screens.main_screen.MainViewModel
import com.example.jetweatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(route = WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }
        composable(route = WeatherScreens.MainScreen.name) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController, viewModel)
        }
    }
}