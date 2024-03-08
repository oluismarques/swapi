package com.swapi.search

import android.os.Parcelable
import com.swapi.tmdb.domain.movie.MovieItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal sealed interface SearchResultUiState : Parcelable {
    @Parcelize
    data class Success(val movieItems: List<MovieItem>) : SearchResultUiState

    @Parcelize
    data class Intro(val movieItems: List<MovieItem>) : SearchResultUiState

    @Parcelize
    data object Error : SearchResultUiState

    @Parcelize
    data object Loading : SearchResultUiState

    @Parcelize
    data object Empty : SearchResultUiState
}
