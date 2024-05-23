package com.swapi.search

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

const val ROUTE_SEARCH = "ROUTE_SEARCH"

fun NavGraphBuilder.searchScreenGraph(
    navigateToDetail: (Int) -> Unit,
) {
    composable(route = ROUTE_SEARCH) {
        val viewModel: SearchViewModel = hiltViewModel()
        val trendingResultUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()
        val searchPagingState = viewModel.searchPagingState.collectAsLazyPagingItems()

        SearchScreen(
            trendingResultUiState = trendingResultUiState,
            searchPagingState = searchPagingState,
            navigateToDetail = navigateToDetail,
            onQueryChange = { viewModel.onEvent(SearchEvent.ChangeQuery(it)) }
        )
    }
}
