package com.example.basicweathercomposable.app.home.data.datasource.remote

import com.example.basicweathercomposable.app.home.data.models.WeatherResponseModel
import com.example.basicweathercomposable.core.network.GET_WEATHER
import com.example.basicweathercomposable.core.network.HttpClient
import com.example.basicweathercomposable.core.network.ResponseWrapper
import javax.inject.Inject

class WeatherRemoteDatasourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : IWeatherRemoteDatasource {

    override suspend fun getWeather(query: String): ResponseWrapper<WeatherResponseModel> {
        return httpClient.get(
            endpoint = GET_WEATHER,
            queryParams = mapOf("q" to query),
            responseType = WeatherResponseModel::class.java
        )
    }

}