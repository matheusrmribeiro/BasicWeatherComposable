package com.example.basicweathercomposable.app.home.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.basicweathercomposable.R
import com.example.basicweathercomposable.app.home.domain.entities.WeatherEntity
import kotlin.math.roundToInt


@Composable
fun SearchBodyComponent(weather: WeatherEntity, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.weatherBackground),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(10.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    weather.city,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                )
                Text(
                    "${weather.temperature.roundToInt()} º",
                    style = TextStyle(fontSize = 60.sp, fontWeight = FontWeight.SemiBold)
                )
            }

            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data("https:${weather.condition.icon}")
                    .build(),
                contentDescription = stringResource(R.string.home_weather_image_description),
                modifier = Modifier.size(83.dp)
            )
        }

    }

}