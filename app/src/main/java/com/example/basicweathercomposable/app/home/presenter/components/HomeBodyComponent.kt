package com.example.basicweathercomposable.app.home.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
fun HomeBodyComponent(weather: WeatherEntity) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        AsyncImage(
            ImageRequest.Builder(LocalContext.current)
                .data("https:${weather.condition.icon}")
                .build(),
            contentDescription = stringResource(R.string.home_weather_image_description),
            modifier = Modifier.size(150.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                weather.city,
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            )

            Box(
                modifier = Modifier.width(11.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(AssistChipDefaults.IconSize),
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            "${weather.temperature.roundToInt()} º",
            style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.SemiBold)
        )

        Spacer(
            modifier = Modifier.height(35.dp)
        )

        Row(
            Modifier
                .fillMaxWidth(0.8f)
                .background(
                    color = colorResource(R.color.weatherBackground),
                    shape = RoundedCornerShape(16.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                Modifier.padding(vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.weather_humidity),
                    color = colorResource(R.color.weatherTitle),
                    style = TextStyle(fontSize = 12.sp)
                )
                Text(
                    "${weather.humidity.roundToInt()}%",
                    color = colorResource(R.color.weatherContent),
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Column(
                Modifier.padding(vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.weather_uv),
                    color = colorResource(R.color.weatherTitle),
                    style = TextStyle(fontSize = 12.sp)
                )
                Text(
                    "${weather.uv.roundToInt()}",
                    color = colorResource(R.color.weatherContent),
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Column(
                Modifier.padding(vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.weather_feels_like),
                    color = colorResource(R.color.weatherTitle),
                    style = TextStyle(fontSize = 12.sp)
                )
                Text(
                    "${weather.feelsLike.roundToInt()}º",
                    color = colorResource(R.color.weatherContent),
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }

}