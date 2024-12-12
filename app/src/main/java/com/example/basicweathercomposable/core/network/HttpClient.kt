package com.example.basicweathercomposable.core.network

import com.example.basicweathercomposable.BuildConfig
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class HttpClient(private var baseUrl: String) {

    private val client = OkHttpClient()
    private val gson = Gson()

    suspend fun <T> get(
        endpoint: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        responseType: Class<T>
    ): ResponseWrapper<T> = withContext(Dispatchers.IO) {
        val urlBuilder = (baseUrl + endpoint).toHttpUrlOrNull()?.newBuilder()

        urlBuilder?.addQueryParameter("key", BuildConfig.WEATHER_API)

        queryParams.forEach { (key, value) ->
            urlBuilder?.addQueryParameter(key, value)
        }

        val url =
            urlBuilder?.build() ?: return@withContext ResponseWrapper.ResponseError("Invalid URL")

        val request = Request.Builder()
            .url(url)
            .apply {
                addHeader("Content-Type", "application/json")

                headers.forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()

        return@withContext try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body.string()
                try {
                    val data = gson.fromJson(body, responseType)
                    ResponseWrapper.ResponseSuccess(data)
                } catch (e: Exception) {
                    ResponseWrapper.ResponseError("JSON parsing error: ${e.message}")
                }
            } else {
                ResponseWrapper.ResponseError("Unexpected code $response")
            }
        } catch (e: Exception) {
            ResponseWrapper.ResponseError(e.message ?: "Unknown error")
        }
    }

    // Additional HTTP methods (PUT, DELETE) can be implemented similarly.
}