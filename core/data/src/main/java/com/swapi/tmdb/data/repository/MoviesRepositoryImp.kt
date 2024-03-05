package com.swapi.tmdb.data.repository

import asMovieDomainModel
import com.swap.util.Resource
import com.swap.util.fetchMovies
import com.swapi.tmdb.data.di.IoDispatcher
import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.domain.MovieItem
import com.swapi.tmdb.domain.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MoviesRepositoryImp @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val moviesService: MoviesService,
) : MoviesRepository {

    override fun trendingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.trendingMovies().movieItemResponses.asMovieDomainModel() }
    }

    override fun popularMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.popularMovies().movieItemResponses.asMovieDomainModel() }
    }

    override fun nowPlayingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.nowPlayingMovies().movieItemResponses.asMovieDomainModel() }
    }

    override fun upcomingMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.upcomingMovies().movieItemResponses.asMovieDomainModel() }
    }

    override fun topRatedMovies(): Flow<Resource<List<MovieItem>>> {
        return fetchMovies(ioDispatcher) { moviesService.topRatedMovies().movieItemResponses.asMovieDomainModel() }
    }
}