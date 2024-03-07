package com.swapi.tmdb.domain.movie

import com.swap.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val mockMovies = listOf(
    MovieItem(
        id = 5276,
        overview = "Dalen",
        releaseDate = null,
        posterUrl = null,
        backdropUrl = null,
        originalTitle = "Donnell",
        voteAverage = 75.027,
        voteCount = 8802
    )
)

class FakeMoviesRepository : MoviesRepository {

    private var shouldFail: Boolean = false

    fun setShouldFail(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    override fun trendingMovies(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockMovies))
        }
    }

    override fun popularMovies(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockMovies))
        }
    }


    override fun nowPlayingMovies(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockMovies))
        }
    }


    override fun upcomingMovies(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockMovies))
        }
    }


    override fun topRatedMovies(): Flow<Resource<List<MovieItem>>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockMovies))
        }
    }


}
