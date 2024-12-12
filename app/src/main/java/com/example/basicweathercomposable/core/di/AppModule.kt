package com.example.basicweathercomposable.core.di

import com.example.basicweathercomposable.app.home.data.datasource.remote.IWeatherRemoteDatasource
import com.example.basicweathercomposable.app.home.data.datasource.remote.WeatherRemoteDatasourceImpl
import com.example.basicweathercomposable.app.home.domain.repository.IWeatherRepository
import com.example.basicweathercomposable.app.home.domain.repository.WeatherRepositoryImpl
import com.example.basicweathercomposable.core.BASE_URL
import com.example.basicweathercomposable.core.network.HttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(BASE_URL)
    }

    @Provides
    fun provideWeatherDatasource(httpClient: HttpClient): IWeatherRemoteDatasource {
        return WeatherRemoteDatasourceImpl(httpClient)
    }

    @Provides
    fun provideWeatherRepository(weatherDatasource: IWeatherRemoteDatasource): IWeatherRepository {
        return WeatherRepositoryImpl(weatherDatasource)
    }

}