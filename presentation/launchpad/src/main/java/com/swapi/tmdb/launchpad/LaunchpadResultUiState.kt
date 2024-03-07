package com.swapi.tmdb.launchpad

import android.os.Parcelable
import com.swapi.tmdb.domain.movie.MovieItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal sealed interface LaunchpadResultUiState : Parcelable {
    @Parcelize
    data class Success(val movieItems: List<MovieItem>) : LaunchpadResultUiState

    @Parcelize
    data object Error : LaunchpadResultUiState

    @Parcelize
    data object Loading : LaunchpadResultUiState

    @Parcelize
    data object Empty : LaunchpadResultUiState
}
