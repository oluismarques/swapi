package com.swapi.tmdb.data.network.service.fake

import DetailResponse
import MoviesResponse
import com.swapi.tmdb.data.network.fake.FakeAssetManager
import com.swapi.tmdb.data.network.service.MoviesService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

internal class FakeMoviesService @Inject constructor(
    private val networkJson: Json,
    private val assets: FakeAssetManager,
) : MoviesService {

    private var shouldFail: Boolean = false

    fun setShouldFail(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun trendingMovies(): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(TRENDING_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun trendingMovies(page: Int): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(TRENDING_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun popularMovies(): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(POPULAR_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun nowPlayingMovies(): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(NOW_PLAYING_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun upcomingMovies(): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(UPCOMING_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun topRatedMovies(): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(TOP_RATED_MOVIES_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieDetail(movieId: Int, credits: String): DetailResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(DETAIL_MOVIE_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun search(query: String, page: Int): MoviesResponse {
        if (shouldFail) {
            throw RuntimeException("Simulated network error")
        }
        return assets.open(DETAIL_MOVIE_ASSET).use(networkJson::decodeFromStream)
    }

    companion object {
        private const val UPCOMING_MOVIES_ASSET = "upcoming_movies.json"
        private const val POPULAR_MOVIES_ASSET = "popular_movies.json"
        private const val TOP_RATED_MOVIES_ASSET = "top_rated_movies.json"
        private const val TRENDING_MOVIES_ASSET = "trending_movies.json"
        private const val NOW_PLAYING_MOVIES_ASSET = "now_playing_movies.json"
        private const val DETAIL_MOVIE_ASSET = "movie_detail.json"
    }
}