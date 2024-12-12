package com.example.basicweathercomposable.app.home.data.models

data class WeatherResponseModel(
    val location: WeatherLocation,
    val current: CurrentWeather
) {

    data class WeatherLocation(
        val name: String
    )

    data class CurrentWeather(
        val temp_c: Double,
        val condition: WeatherCondition,
        val humidity: Double,
        val feelslike_c: Double,
        val uv: Double
    )

    data class WeatherCondition(
        val text: String,
        val icon: String,
        val code: Int
    )
}