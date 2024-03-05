package com.swapi.tmdb.launchpad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swap.util.Resource
import com.swap.util.asMutable
import com.swapi.tmdb.domain.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class LaunchpadViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    val trendingUiState: StateFlow<LaunchpadResultUiState> =
        MutableStateFlow<LaunchpadResultUiState>(LaunchpadResultUiState.Empty)


    val popularUiState: StateFlow<LaunchpadResultUiState> =
        MutableStateFlow<LaunchpadResultUiState>(LaunchpadResultUiState.Empty)


    val upcomingUiState: StateFlow<LaunchpadResultUiState> =
        MutableStateFlow<LaunchpadResultUiState>(LaunchpadResultUiState.Empty)


    val nowPlayingUiState: StateFlow<LaunchpadResultUiState> =
        MutableStateFlow<LaunchpadResultUiState>(LaunchpadResultUiState.Empty)


    val topRatedUiState: StateFlow<LaunchpadResultUiState> =
        MutableStateFlow<LaunchpadResultUiState>(LaunchpadResultUiState.Empty)

    init {
        getTrendingMovies()
        getPopularMovies()
        getUpcomingMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
    }

    private fun getTrendingMovies() {
        moviesRepository.trendingMovies().onEach { result ->
            when (result) {
                is Resource.Error -> trendingUiState.asMutable().emit(LaunchpadResultUiState.Error)
                Resource.Loading -> trendingUiState.asMutable().emit(LaunchpadResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) trendingUiState.asMutable()
                    .emit(LaunchpadResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        moviesRepository.popularMovies().onEach { result ->
            when (result) {
                is Resource.Error -> popularUiState.asMutable().emit(LaunchpadResultUiState.Error)
                Resource.Loading -> popularUiState.asMutable().emit(LaunchpadResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) popularUiState.asMutable()
                    .emit(LaunchpadResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies() {
        moviesRepository.upcomingMovies().onEach { result ->
            when (result) {
                is Resource.Error -> upcomingUiState.asMutable().emit(LaunchpadResultUiState.Error)
                Resource.Loading -> upcomingUiState.asMutable().emit(LaunchpadResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) upcomingUiState.asMutable()
                    .emit(LaunchpadResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }

    private fun getNowPlayingMovies() {
        moviesRepository.nowPlayingMovies().onEach { result ->
            when (result) {
                is Resource.Error -> nowPlayingUiState.asMutable().emit(LaunchpadResultUiState.Error)
                Resource.Loading -> nowPlayingUiState.asMutable().emit(LaunchpadResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) nowPlayingUiState.asMutable()
                    .emit(LaunchpadResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() {
        moviesRepository.topRatedMovies().onEach { result ->
            when (result) {
                is Resource.Error -> topRatedUiState.asMutable().emit(LaunchpadResultUiState.Error)
                Resource.Loading -> topRatedUiState.asMutable().emit(LaunchpadResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) topRatedUiState.asMutable()
                    .emit(LaunchpadResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }
}