package com.swapi.tmdb.domain.detail

import com.swap.util.Resource
import com.swapi.tmdb.domain.movie.MovieItem
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    fun getMovieDetail(id: Int): Flow<Resource<DetailItem>>

}

