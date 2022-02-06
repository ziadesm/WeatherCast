package com.example.jetweatherforecast.screens.main_screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.WeatherResponse
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDateTime
import com.example.jetweatherforecast.utils.formatDecimals
import com.example.jetweatherforecast.widget.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<WeatherResponse, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = viewModel.getWeather("Seattle")
    }.value
    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(navController, weatherData.data!!)
    }
}

@Composable
fun MainScaffold(
    navController: NavController,
    weatherData: WeatherResponse
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = weatherData.city.name + ", ${weatherData.city.country}",
                elevation = 5.dp
            )
        }
    ) {
        MainContent(weatherData, navController)
    }
}

@Composable
fun MainContent(weatherData: WeatherResponse, navController: NavController) {
    val weatherItem = weatherData.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                modifier = Modifier
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,

                )
                Text(
                    text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic,

                )
            }
        }
        HumidityWindPressureRow(weatherItem = weatherItem)
        Divider()
        SunsetSunriseRow(weatherItem = weatherItem)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.h4
        )
        CreateDetailsLazyRow(weatherData.list)
    }
}

@Composable
fun CreateDetailsLazyRow(list: List<WeatherResponse.WeatherDayListModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(4.dp)
    ) {
        items(list) { item ->
            WeatherDetailsRow(item)
        }
    }
}

@Composable
fun WeatherDetailsRow(item: WeatherResponse.WeatherDayListModel) {
    val imageUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}.png"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomEnd = 8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.temp.night.toString())
    }
}

@Composable
fun SunsetSunriseRow(weatherItem: WeatherResponse.WeatherDayListModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.sunrise),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = formatDateTime(weatherItem.sunrise),
                style = MaterialTheme.typography.caption
            )
        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.sunset),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = formatDateTime(weatherItem.sunset),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherResponse.WeatherDayListModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.humidity),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.humidity}%",
                style = MaterialTheme.typography.caption
            )
        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.pressure),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.pressure} psi",
                style = MaterialTheme.typography.caption
            )
        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.wind),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weatherItem.humidity} mph",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Icon",
        modifier = Modifier
            .size(80.dp)
    )
}
