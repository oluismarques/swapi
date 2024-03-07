package com.swapi.tmdb.launchpad

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_LAUNCHPAD = "ROUTE_LAUNCHPAD"

fun NavGraphBuilder.launchpadScreenGraph(
    navigateToDetail: (Int) -> Unit
) {
    composable(route = ROUTE_LAUNCHPAD) {
        val viewModel: LaunchpadViewModel = hiltViewModel()
        val trendingResultUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()
        val popularResultUiState by viewModel.popularUiState.collectAsStateWithLifecycle()
        val nowPlayingUiState by viewModel.nowPlayingUiState.collectAsStateWithLifecycle()
        val upcomingUiState by viewModel.upcomingUiState.collectAsStateWithLifecycle()
        val topRatedUiState by viewModel.topRatedUiState.collectAsStateWithLifecycle()

        LaunchPadScreen(
            trendingResultUiState = trendingResultUiState,
            popularResultUiState = popularResultUiState,
            upcomingUiState = upcomingUiState,
            nowPlayingUiState = nowPlayingUiState,
            topRatedUiState = topRatedUiState,
            navigateToDetail = navigateToDetail
        )
    }
}
