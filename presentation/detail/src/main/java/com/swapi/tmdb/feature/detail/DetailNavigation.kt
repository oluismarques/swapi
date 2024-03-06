package com.swapi.tmdb.feature.detail

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val ARG_ID = "ARG_ID"
private const val ROUTE_DETAIL_SCREEN = "ROUTE_DETAIL_SCREEN/{$ARG_ID}"

internal data class DetailArgs(
    val id: Int,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = checkNotNull(savedStateHandle[ARG_ID]),
    )
}

fun NavController.navigateToDetail(
    id: Int,
    builder: NavOptionsBuilder.() -> Unit,
) {
    val route = ROUTE_DETAIL_SCREEN.replace("{$ARG_ID}", id.toString())
    navigate(route, builder)
}

fun NavGraphBuilder.detailScreenGraph(navController: NavController) {
    composable(
        route = ROUTE_DETAIL_SCREEN,
        arguments = listOf(
            navArgument(ARG_ID) { type = NavType.IntType },
        ),
    ) {
        val viewModel: DetailViewModel = hiltViewModel()

        val detailResultUiState by viewModel.detailResultUiState.collectAsStateWithLifecycle()

        DetailScreen(
            detailResultUiState = detailResultUiState,
            onBackButtonClick = navController::popBackStack
        )
    }
}
