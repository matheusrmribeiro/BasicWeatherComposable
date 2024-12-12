package com.example.basicweathercomposable.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

fun savePreferences(context: Context, key: String, data: Any) {
    val gson = Gson()
    val json = gson.toJson(data)
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString(key, json)
        apply()
    }
}

inline fun <reified T> getPreferences(context: Context, key: String): T? {
    val gson = Gson()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(key, null)
    return if (json != null) gson.fromJson(json, T::class.java) else null
}