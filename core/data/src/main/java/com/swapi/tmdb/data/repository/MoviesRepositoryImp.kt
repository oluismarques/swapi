package com.swapi.tmdb.data.repository

import asDomainModel
import com.swap.util.Resource
import com.swap.util.fetchMovies
import com.swapi.tmdb.data.di.IoDispatcher
import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.domain.movie.MovieItem
import com.swapi.tmdb.domain.movie.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MoviesRepositoryImp @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val moviesService: MoviesService,
) : MoviesRepository {

    override fun trendingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.trendingMovies().movieItemResponses.asDomainModel() }
    }

    override fun popularMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.popularMovies().movieItemResponses.asDomainModel() }
    }

    override fun nowPlayingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.nowPlayingMovies().movieItemResponses.asDomainModel() }
    }

    override fun upcomingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.upcomingMovies().movieItemResponses.asDomainModel() }
    }

    override fun topRatedMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.topRatedMovies().movieItemResponses.asDomainModel() }
    }
}