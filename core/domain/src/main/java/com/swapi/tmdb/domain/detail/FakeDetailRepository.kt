package com.swapi.tmdb.domain.detail

import com.swap.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val mockDetailItem = DetailItem(
    backdropPath = null,
    genres = listOf(),
    homepage = null,
    id = 6813,
    originalLanguage = "Keandra",
    originalTitle = "Marius",
    overview = "Tedrick",
    popularity = 92.198,
    posterPath = null,
    releaseDate = "2022/04/03",
    spokenLanguages = listOf(),
    status = "Jillian",
    tagline = "Marquis",
    title = "Joanie",
    voteAverage = 24.574,
    voteCount = 8081,
    runtime = "123",
    credits = Credits(cast = listOf(), crew = listOf())
)

class FakeDetailRepository : DetailRepository {

    private var shouldFail: Boolean = false

    fun setShouldFail(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<DetailItem>> = flow {
        emit(Resource.Loading)

        if (shouldFail) {
            emit(Resource.Error("Failed to fetch movie detail"))
        } else {
            emit(Resource.Success(mockDetailItem))
        }
    }

}
