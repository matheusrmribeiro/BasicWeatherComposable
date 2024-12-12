package com.example.basicweathercomposable.app.home.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicweathercomposable.R


@Composable
fun EmptyComponent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.home_empty_title),
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            stringResource(R.string.home_empty_subtitle),
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
        )
    }

}