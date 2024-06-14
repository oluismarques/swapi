package com.swapi.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.swap.util.Resource
import com.swap.util.asMutable
import com.swapi.tmdb.domain.movie.MovieItem
import com.swapi.tmdb.domain.movie.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    val trendingUiState: StateFlow<SearchResultUiState> =
        MutableStateFlow<SearchResultUiState>(SearchResultUiState.Empty)

    val searchPagingState: StateFlow<PagingData<MovieItem>> =
        MutableStateFlow(PagingData.empty())

    private var searchJob: Job? = null
    fun onEvent(event: SearchEvent) = when (event) {
        is SearchEvent.ChangeQuery -> onQueryChanged(event.value)
    }

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        moviesRepository.trendingMovies().onEach { result ->
            when (result) {
                is Resource.Error -> trendingUiState.asMutable().emit(SearchResultUiState.Error)
                Resource.Loading -> trendingUiState.asMutable().emit(SearchResultUiState.Loading)
                is Resource.Success -> if (result.data.isNotEmpty()) trendingUiState.asMutable()
                    .emit(SearchResultUiState.Success(result.data))
            }
        }.launchIn(viewModelScope)
    }

    private fun onQueryChanged(query: String) {
        searchDebounced(query)
    }

    private fun searchDebounced(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            trendingUiState.asMutable().emit(SearchResultUiState.Loading)
            delay(DEBOUNCE_TIME_MILLS)
            moviesRepository.search(query).cachedIn(viewModelScope).collect { result ->
                trendingUiState.asMutable().emit(SearchResultUiState.IsSearching)
                searchPagingState.asMutable().emit(result)


            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIME_MILLS = 300L
        private const val KEY_STATE = "state"
        private const val KEY_SEARCH_QUERY = "searchQuery"
    }
}