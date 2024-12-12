package com.example.basicweathercomposable.app.home.domain.repository

import com.example.basicweathercomposable.app.home.domain.entities.WeatherEntity
import com.example.basicweathercomposable.core.network.ResponseWrapper

interface IWeatherRepository {

    suspend fun getWeather(query: String): ResponseWrapper<WeatherEntity>

}