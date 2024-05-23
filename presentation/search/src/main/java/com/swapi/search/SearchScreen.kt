package com.swapi.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.swapi.designsystem.component.DSMovieCard
import com.swapi.designsystem.component.DSSearchTopBar
import com.swapi.designsystem.component.ThemePreviews
import com.swapi.designsystem.theme.Dimen12
import com.swapi.designsystem.theme.Dimen8
import com.swapi.tmdb.domain.movie.MovieItem
import com.swapi.tmdb.feature.search.R
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
internal fun SearchScreen(
    trendingResultUiState: SearchResultUiState,
    navigateToDetail: (Int) -> Unit,
    onQueryChange: (String) -> Unit,
    searchPagingState: LazyPagingItems<MovieItem>,
) {
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = ""))
    }

    val listState = rememberLazyListState()


    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                DSSearchTopBar(
                    modifier = Modifier.padding(horizontal = Dimen12),
                    value = textFieldValue,
                    onQueryChange = {
                        textFieldValue = it
                        onQueryChange.invoke(it.text)
                    },
                )
            }

            item {
                when {
                    searchPagingState.loadState.refresh is LoadState.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(44.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            LaunchedEffect(true) { listState.scrollToItem(0) }
                        }
                    }

                    searchPagingState.itemCount == 0 || searchPagingState.loadState.refresh is LoadState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.testTag("empty_tag"),
                                text = stringResource(R.string.search_empty_results)
                            )
                        }
                    }


                    else -> {
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
                                Text(text = "Search results")

                                TextButton(
                                    onClick = {}
                                ) {
                                    Text(text = stringResource(id = R.string.search_movies_see_all))
                                }
                            }
                            LazyRow(
                                modifier = Modifier,
                            ) {
                                items(searchPagingState.itemCount) { index ->
                                    val item = searchPagingState[index]
                                    DSMovieCard(
                                        modifier = Modifier
                                            .padding(end = Dimen8)
                                            .testTag("movie_card_tag"),
                                        name = item?.originalTitle.orEmpty(),
                                        onCardClick = { },
                                        imageUrl = item?.posterUrl,
                                        voteAverage = item?.voteAverage.toString(),
                                        releaseDate = item?.releaseDate.orEmpty()
                                    )
                                }
                            }
                        }
                    }
                }
            }


            item {
                if (trendingResultUiState !is SearchResultUiState.IsSearching) {
                    MovieCollection(
                        uiState = trendingResultUiState,
                        title = "tending",
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieCollection(
    uiState: SearchResultUiState,
    title: String,
    navigateToDetail: (Int) -> Unit,
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
                Text(text = stringResource(id = R.string.search_movies_see_all))
            }
        }
        when (uiState) {
            SearchResultUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.testTag("empty_tag"),
                        text = stringResource(R.string.search_empty_results)
                    )
                }
            }

            SearchResultUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.testTag("error_tag"),
                        text = stringResource(R.string.search_error_message)
                    )
                }
            }

            SearchResultUiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.testTag("loading_tag"))
            }

            is SearchResultUiState.Success -> {
                MovieList(
                    modifier = Modifier.fillMaxWidth(),
                    movieItems = uiState.movieItems,
                    onMovieClick = {
                        navigateToDetail.invoke(it.id)
                    }
                )
            }

            is SearchResultUiState.Intro -> {
                MovieList(
                    modifier = Modifier.fillMaxWidth(),
                    movieItems = uiState.movieItems,
                    onMovieClick = {
                        navigateToDetail.invoke(it.id)
                    }
                )
            }

            SearchResultUiState.IsSearching -> {}
        }
    }
}

@Composable
private fun MovieList(
    movieItems: List<MovieItem>,
    onMovieClick: (MovieItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
    ) {
        itemsIndexed(movieItems) { index, item ->
            DSMovieCard(
                modifier = Modifier
                    .padding(end = Dimen8)
                    .testTag("movie_card_tag"),
                name = item.originalTitle,
                onCardClick = { onMovieClick.invoke(item) },
                imageUrl = item.posterUrl,
                voteAverage = item.voteAverage.toString(),
                releaseDate = item.releaseDate.orEmpty()
            )
        }
    }
}

@ThemePreviews
@Composable
private fun SearchScreenPreview() {
    val pagingData = MutableStateFlow(PagingData.empty<MovieItem>())
    SearchScreen(
        trendingResultUiState = SearchResultUiState.Success(
            movieItems = listOf(
                MovieItem(
                    id = 6434,
                    releaseDate = null,
                    posterUrl = null,
                    originalTitle = "Gina",
                    voteAverage = 17.937,
                )
            )
        ),
        navigateToDetail = {},
        onQueryChange = {},
        searchPagingState = pagingData.collectAsLazyPagingItems()
    )
}
