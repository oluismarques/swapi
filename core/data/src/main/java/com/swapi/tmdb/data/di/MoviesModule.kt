package com.swapi.tmdb.data.di

import com.swapi.tmdb.data.repository.MoviesRepositoryImp
import com.swapi.tmdb.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MoviesModule {

    @Binds
    @Singleton
    fun bindsMoviesRepository(
        impl: MoviesRepositoryImp,
    ): MoviesRepository

}
