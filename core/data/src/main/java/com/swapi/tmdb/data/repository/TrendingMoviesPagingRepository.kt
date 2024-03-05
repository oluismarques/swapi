package com.swapi.tmdb.data.repository

import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.data.paging.TrendingMoviesPagingSource
import com.swapi.tmdb.domain.MovieItem
import com.swapi.tmdb.domain.base.BasePagingRepository
import com.swapi.tmdb.domain.base.BasePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TrendingMoviesPagingRepository @Inject constructor(
    private val movieApi: MoviesService,
) : BasePagingRepository<MovieItem>() {

    override fun pagingSource(query: String?, id: Int?): BasePagingSource<MovieItem> =
        TrendingMoviesPagingSource(movieApi)
}