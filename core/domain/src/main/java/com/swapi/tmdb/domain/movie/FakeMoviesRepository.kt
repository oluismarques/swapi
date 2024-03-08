package com.swapi.tmdb.domain.movie

import androidx.paging.PagingData
import com.swap.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val mockMovies = listOf(
    MovieItem(
        id = 5276,
        releaseDate = "2220/03/30",
        posterUrl = null,
        originalTitle = "Donnell",
        voteAverage = 75.027,
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

    override fun search(query: String): Flow<PagingData<MovieItem>> {
        return flow {
            emit(PagingData.empty())
        }
    }


}
