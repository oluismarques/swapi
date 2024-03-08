package com.swapi.tmdb.data.network.service

import DetailResponse
import MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesService {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): MoviesResponse

    @GET("3/movie/popular")
    suspend fun popularMovies(): MoviesResponse

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): MoviesResponse

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): MoviesResponse

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): MoviesResponse

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(@Query("page") page: Int): MoviesResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") credits: String = "credits",
    ): DetailResponse

    @GET("3/search/movie")
    fun search(
        @Query("Query") query: String,
        @Query("page") page: Int = 20,
    ): MoviesResponse

}