package com.swapi.tmdb.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swap.util.Resource
import com.swap.util.asMutable
import com.swapi.tmdb.domain.detail.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailRepository: DetailRepository,
) : ViewModel() {

    private var detailArgs = DetailArgs(savedStateHandle)

    val detailResultUiState: StateFlow<DetailResultUiState> =
        MutableStateFlow<DetailResultUiState>(DetailResultUiState.Loading)

    init {
        detailRepository.getMovieDetail(detailArgs.id).onEach {
            when (it) {
                is Resource.Error -> {
                    detailResultUiState.asMutable().emit(DetailResultUiState.Error)
                }

                Resource.Loading -> {
                    detailResultUiState.asMutable().emit(DetailResultUiState.Loading)
                }

                is Resource.Success -> {
                    detailResultUiState.asMutable()
                        .emit(DetailResultUiState.Success(it.data))
                }
            }
        }.launchIn(viewModelScope)
    }
}