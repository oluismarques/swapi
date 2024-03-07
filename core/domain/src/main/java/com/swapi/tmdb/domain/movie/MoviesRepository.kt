package com.swapi.tmdb.domain.movie

import com.swap.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun trendingMovies(): Flow<Resource<List<MovieItem>>>

    fun popularMovies(): Flow<Resource<List<MovieItem>>>

    fun nowPlayingMovies(): Flow<Resource<List<MovieItem>>>

    fun upcomingMovies(): Flow<Resource<List<MovieItem>>>

    fun topRatedMovies(): Flow<Resource<List<MovieItem>>>

}

