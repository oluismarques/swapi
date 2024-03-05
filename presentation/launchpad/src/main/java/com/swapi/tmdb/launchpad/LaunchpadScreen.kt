package com.swapi.tmdb.launchpad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swapi.designsystem.component.DSMovieCard
import com.swapi.designsystem.component.DSTopBar
import com.swapi.designsystem.component.ThemePreviews
import com.swapi.designsystem.theme.Dimen8
import com.swapi.tmdb.domain.MovieItem


@Composable
internal fun LaunchPadScreen(
    trendingResultUiState: LaunchpadResultUiState,
    popularResultUiState: LaunchpadResultUiState,
    upcomingUiState: LaunchpadResultUiState,
    nowPlayingUiState: LaunchpadResultUiState,
    topRatedUiState: LaunchpadResultUiState,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 40.dp))
                    )
                )
            }

            item {
                MovieCollection(
                    uiState = trendingResultUiState,
                    title = stringResource(id = R.string.launches_movies_trending)
                )
                MovieCollection(
                    uiState = popularResultUiState,
                    title = stringResource(id = R.string.launches_movies_popular)
                )
                MovieCollection(
                    uiState = nowPlayingUiState,
                    title = stringResource(id = R.string.launches_movies_now_playing)
                )
                MovieCollection(
                    uiState = upcomingUiState,
                    title = stringResource(id = R.string.launches_movies_upcoming)
                )
                MovieCollection(
                    uiState = topRatedUiState,
                    title = stringResource(id = R.string.launches_movies_top_rated)
                )
            }
        }

        DSTopBar(
            onSearchClick = { }, title = stringResource(R.string.launches_top_bar_title)
        )
    }
}

@Composable
private fun MovieCollection(
    uiState: LaunchpadResultUiState,
    title: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimen8, top = Dimen8, bottom = Dimen8),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = Dimen8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)

            TextButton(
               onClick = {}
            ) {
                Text(text = stringResource(id = R.string.launches_movies_see_all))
            }
        }

        when (uiState) {
            LaunchpadResultUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.launchpad_empty_results))
                }
            }

            LaunchpadResultUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.launchpad_error_message))
                }
            }

            LaunchpadResultUiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is LaunchpadResultUiState.Success -> {
                MovieList(
                    modifier = Modifier.fillMaxWidth(),
                    feeds = uiState.movieItems,
                    onFeedClick = {}
                )
            }
        }
    }
}

@Composable
private fun MovieList(
    feeds: List<MovieItem>,
    onFeedClick: (MovieItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
    ) {
        itemsIndexed(feeds) { index, item ->
            DSMovieCard(
                modifier = Modifier.padding(end = Dimen8),
                name = item.originalTitle,
                onFeedClick = { },
                imageUrl = item.posterUrl
            )
        }
    }
}

@ThemePreviews
@Composable
private fun LaunchPadScreenPreview() {
    LaunchPadScreen(
        trendingResultUiState = LaunchpadResultUiState.Success(
            movieItems = listOf(
                MovieItem(
                    id = 6434,
                    overview = "Otha",
                    releaseDate = null,
                    posterUrl = null,
                    backdropUrl = null,
                    originalTitle = "Gina",
                    voteAverage = 17.937,
                    voteCount = 9507
                )
            )
        ),
        popularResultUiState = LaunchpadResultUiState.Loading,
        upcomingUiState = LaunchpadResultUiState.Empty,
        nowPlayingUiState = LaunchpadResultUiState.Empty,
        topRatedUiState = LaunchpadResultUiState.Empty
    )
}
