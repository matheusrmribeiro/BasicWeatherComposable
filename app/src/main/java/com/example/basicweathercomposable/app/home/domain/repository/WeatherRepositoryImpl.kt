package com.example.basicweathercomposable.app.home.domain.repository

import com.example.basicweathercomposable.app.home.data.datasource.remote.IWeatherRemoteDatasource
import com.example.basicweathercomposable.app.home.domain.entities.WeatherEntity
import com.example.basicweathercomposable.core.network.ResponseWrapper
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val datasource: IWeatherRemoteDatasource
) : IWeatherRepository {

    override suspend fun getWeather(query: String): ResponseWrapper<WeatherEntity> {
        return when (val response = datasource.getWeather(query)) {
            is ResponseWrapper.ResponseError -> {
                response
            }
            is ResponseWrapper.ResponseSuccess -> {
                val data = WeatherEntity.mapper(response.data)
                ResponseWrapper.ResponseSuccess(data)
            }
        }
    }

}