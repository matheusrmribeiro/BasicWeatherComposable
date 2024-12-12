package com.example.basicweathercomposable.app.home.presenter

import androidx.lifecycle.viewModelScope
import com.example.basicweathercomposable.app.home.domain.entities.HomeDataEntity
import com.example.basicweathercomposable.app.home.domain.entities.WeatherEntity
import com.example.basicweathercomposable.app.home.domain.repository.IWeatherRepository
import com.example.basicweathercomposable.core.base.BaseViewModel
import com.example.basicweathercomposable.core.base.ViewState
import com.example.basicweathercomposable.core.network.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias WeatherState = ViewState<HomeDataEntity?>

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : BaseViewModel() {
    var viewState = MutableStateFlow<WeatherState>(
        ViewState.ViewStateSuccess(
            HomeDataEntity(
                isSearching = false,
                data = null
            )
        )
    )
        private set

    fun getData(query: String) {
        try {
            viewState.value = ViewState.ViewStateLoading()
            viewModelScope.launch {
                delay(2000)
                viewState.value = when (val response = weatherRepository.getWeather(query)) {
                    is ResponseWrapper.ResponseError -> {
                        if (response.errorMessage.contains("code=400")) {
                            ViewState.ViewStateSuccess(
                                HomeDataEntity(
                                    isSearching = true,
                                    data = null
                                )
                            )
                        } else {
                            ViewState.ViewStateError(response.errorMessage)
                        }
                    }

                    is ResponseWrapper.ResponseSuccess -> {
                        ViewState.ViewStateSuccess(
                            HomeDataEntity(
                                isSearching = true,
                                data = response.data
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            viewState.value = ViewState.ViewStateError("Failed to fetch data")
        }
    }

    fun setSelectedWeather(weather: WeatherEntity) {
        viewState.value = ViewState.ViewStateSuccess(
            HomeDataEntity(
                isSearching = false,
                data = weather
            )
        )
    }

}