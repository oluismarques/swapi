package com.swapi.search

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SEARCH = "ROUTE_SEARCH"

fun NavGraphBuilder.searchScreenGraph(
    navigateToDetail: (Int) -> Unit,
) {
    composable(route = ROUTE_SEARCH) {
        val viewModel: SearchViewModel = hiltViewModel()
        val trendingResultUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()

        SearchScreen(
            trendingResultUiState = trendingResultUiState,
            navigateToDetail = navigateToDetail,
            onQueryChange = { viewModel.onEvent(SearchEvent.ChangeQuery(it)) }
        )
    }
}
