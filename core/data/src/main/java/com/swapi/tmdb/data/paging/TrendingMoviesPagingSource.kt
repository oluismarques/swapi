package com.swapi.tmdb.data.paging

import asDomainModel
import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.domain.movie.MovieItem
import com.swapi.tmdb.domain.base.BasePagingSource

internal class TrendingMoviesPagingSource(
    private val movieApi: MoviesService,
) : BasePagingSource<MovieItem>() {

    override suspend fun fetchItems(page: Int): List<MovieItem> =
        movieApi.trendingMovies(page).movieItemResponses.asDomainModel()
}