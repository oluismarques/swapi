package com.swapi.tmdb.data.repository

import asDetailDomainModel
import com.swap.util.Resource
import com.swap.util.fetchMovies
import com.swapi.tmdb.data.di.IoDispatcher
import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.domain.detail.DetailItem
import com.swapi.tmdb.domain.detail.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DetailRepositoryImp @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val moviesService: MoviesService,
) : DetailRepository {


    override fun getMovieDetail(id: Int): Flow<Resource<DetailItem>> {
        return fetchMovies(ioDispatcher) { moviesService.getMovieDetail(id).asDetailDomainModel() }
    }
}