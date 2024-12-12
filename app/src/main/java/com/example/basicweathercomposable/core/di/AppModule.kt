package com.example.basicweathercomposable.core.di

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

}