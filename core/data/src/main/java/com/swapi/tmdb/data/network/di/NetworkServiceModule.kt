package com.swapi.tmdb.data.network.di

import com.swapi.tmdb.data.network.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {

    @Provides
    @Singleton
    internal fun provideCompanyInfoService(retrofit: Retrofit): MoviesService =
        retrofit.create()

}
