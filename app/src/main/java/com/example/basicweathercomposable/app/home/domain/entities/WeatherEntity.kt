package com.example.basicweathercomposable.app.home.domain.entities

import com.example.basicweathercomposable.app.home.data.models.WeatherResponseModel

data class WeatherEntity(
    val city: String,
    val temperature: Double,
    val condition: WeatherCondition,
    val humidity: Double,
    val fellsLike: Double,
    val uv: Double
) {

    data class WeatherCondition(
        val text: String,
        val icon: String,
    )

    companion object {

        fun mapper(response: WeatherResponseModel) = WeatherEntity(
            city = response.location.name,
            temperature = response.current.temp_c,
            condition = WeatherCondition(
                text = response.current.condition.text,
                icon = response.current.condition.icon
            ),
            humidity = response.current.humidity,
            fellsLike = response.current.fellslike_c,
            uv = response.current.uv,
        )
    }
}