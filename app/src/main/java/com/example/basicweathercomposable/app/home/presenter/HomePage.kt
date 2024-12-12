package com.example.basicweathercomposable.app.home.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.basicweathercomposable.R
import com.example.basicweathercomposable.app.home.domain.entities.WeatherEntity
import com.example.basicweathercomposable.app.home.presenter.components.EmptyComponent
import com.example.basicweathercomposable.app.home.presenter.components.HomeBodyComponent
import com.example.basicweathercomposable.app.home.presenter.components.SearchBodyComponent
import com.example.basicweathercomposable.core.WEATHER_PREF
import com.example.basicweathercomposable.core.utils.HandleState
import com.example.basicweathercomposable.core.utils.getPreferences
import com.example.basicweathercomposable.core.utils.savePreferences

@Composable
fun HomePage(navController: NavController)  {

    val viewModel: HomeViewModel = hiltViewModel()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        getPreferences<WeatherEntity>(context, WEATHER_PREF)?.let {
            viewModel.setSelectedWeather(it)
        }
    }

    HandleState(
        state = viewModel.viewState.collectAsStateWithLifecycle(),
        error = { message ->
            Text(stringResource(R.string.fetch_error, message))
        },
    ) { weather ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.getData(searchQuery.text)
                        keyboardController?.hide()
                    }
                ),
                placeholder = { Text(stringResource(R.string.home_search_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.textField),
                    unfocusedContainerColor = colorResource(id = R.color.textField),
                    disabledContainerColor = colorResource(id = R.color.textField),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,

                    ),
                trailingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = colorResource(id = R.color.placeholder)
                    )
                },
                singleLine = true
            )

            if (weather == null) {
                EmptyComponent()
                return@HandleState
            }

            if (weather.isSearching) {
                if (weather.data != null) {
                    SearchBodyComponent(
                        weather = weather.data
                    ) {
                        viewModel.setSelectedWeather(weather.data)
                        savePreferences(context, WEATHER_PREF, weather.data)
                    }
                }
                return@HandleState
            }

            if (weather.data == null) {
                EmptyComponent()
                return@HandleState
            }

            HomeBodyComponent(
                weather = weather.data
            )
        }

    }

}