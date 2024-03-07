package com.swapi.tmdb.data.di

import com.swapi.tmdb.data.repository.DetailRepositoryImp
import com.swapi.tmdb.data.repository.MoviesRepositoryImp
import com.swapi.tmdb.domain.detail.DetailRepository
import com.swapi.tmdb.domain.movie.MoviesRepository
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

    @Binds
    @Singleton
    fun bindsDetailRepository(
        impl: DetailRepositoryImp,
    ): DetailRepository

}
