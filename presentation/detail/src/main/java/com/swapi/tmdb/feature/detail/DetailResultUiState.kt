package com.swapi.tmdb.feature.detail

import android.os.Parcelable
import com.swapi.tmdb.domain.detail.DetailItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal sealed interface DetailResultUiState : Parcelable {
    @Parcelize
    data class Success(val detailItem: DetailItem) : DetailResultUiState

    @Parcelize
    data object Error : DetailResultUiState

    @Parcelize
    data object Loading : DetailResultUiState
}
