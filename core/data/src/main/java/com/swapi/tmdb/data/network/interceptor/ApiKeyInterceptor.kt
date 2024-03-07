package com.swapi.tmdb.data.network.interceptor

import com.swapi.tmdb.core.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


internal class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add API key as a query parameter
        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(modifiedRequest)
    }
}
