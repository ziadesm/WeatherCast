package com.example.jetweatherforecast.widget
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title: String = "Seattle",
    icon: ImageVector? = null,
    isMain: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddAction: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            ) },
        actions = {
            if (isMain) {
                IconButton(onClick = {},
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = {},
                ) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More ")
                }
            } else Box() {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            onButtonClicked()
                        }
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}