package com.example.basicweathercomposable.core.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.basicweathercomposable.core.base.ViewState

@Composable
fun <T> HandleState(
    state: State<ViewState<T>>,
    error: @Composable (message: String) -> Unit,
    builder: @Composable (data: T?) -> Unit,
) {
    var data by remember { mutableStateOf<T?>(null) }

    when (val currentState = state.value) {
        is ViewState.ViewStateLoading -> {
            Column {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
                builder(data)
            }
        }
        is ViewState.ViewStateSuccess -> {
            data = currentState.data
            builder(currentState.data)
        }
        is ViewState.ViewStateError -> {
            error(currentState.errorMessage)
        }
    }
}