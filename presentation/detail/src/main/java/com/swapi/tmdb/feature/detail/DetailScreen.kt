package com.swapi.tmdb.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.swapi.designsystem.component.ThemePreviews
import com.swapi.tmdb.feature.detail.components.DetailsItem


@Composable
internal fun DetailScreen(
    detailResultUiState: DetailResultUiState,
    onBackButtonClick: () -> Unit,
) {
    DetailsContent(
        uiState = detailResultUiState,
        onBackButtonClick = onBackButtonClick
    )
}

@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    uiState: DetailResultUiState,
    onBackButtonClick: () -> Unit,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            DetailResultUiState.Error -> {
                Box(modifier = modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.testTag("error_tag"),
                        text = stringResource(R.string.detail_something_went_wrong)
                    )
                }
            }

            DetailResultUiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .testTag("loading_tag")
                            .align(Alignment.Center)
                    )
                }
            }

            is DetailResultUiState.Success -> with(uiState.detailItem) {
                DetailsItem(
                    title = title,
                    overview = overview,
                    posterPath = posterPath,
                    releaseDate = releaseDate,
                    runtime = runtime,
                    genres = genres,
                    voteAverage = voteAverage.toString(),
                    credits = credits,
                    voteCount = voteCount.toString(),
                    onBackButtonClick = onBackButtonClick,
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun DetailScreenPreview() {
    DetailScreen(detailResultUiState = DetailResultUiState.Loading, onBackButtonClick = {})
}
