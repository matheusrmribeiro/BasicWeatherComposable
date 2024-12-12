package com.example.basicweathercomposable.core.base

import androidx.lifecycle.ViewModel
import com.example.basicweathercomposable.core.network.HttpClient
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var httpClient: HttpClient

}