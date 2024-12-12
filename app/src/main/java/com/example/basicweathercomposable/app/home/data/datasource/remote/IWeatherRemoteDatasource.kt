package com.example.basicweathercomposable.app.home.data.datasource.remote

import com.example.basicweathercomposable.app.home.data.models.WeatherResponseModel
import com.example.basicweathercomposable.core.network.ResponseWrapper

interface IWeatherRemoteDatasource {

    /**
     * Get the current Weather at given location
     * @param query: The city name (See [WeatherAPI](https://www.weatherapi.com/docs/) for more)
     */
    suspend fun getWeather(query: String): ResponseWrapper<WeatherResponseModel>

}