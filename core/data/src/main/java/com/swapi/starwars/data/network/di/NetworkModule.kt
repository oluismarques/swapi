package com.swapi.starwars.data.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    internal fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (com.swapi.starwars.core.data.BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        okhttpCallFactory: Call.Factory,
        networkJson: Json,
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(com.swapi.starwars.core.data.BuildConfig.BaseUrl)
            .callFactory(okhttpCallFactory)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    internal fun okHttpCallFactory(httpLoggingInterceptor: HttpLoggingInterceptor): Call.Factory =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

}

