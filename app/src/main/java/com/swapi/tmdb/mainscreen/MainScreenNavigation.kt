package com.swapi.tmdb.mainscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val ROUTE_MAIN_SCREEN = "ROUTE_MAIN_SCREEN"

fun NavGraphBuilder.mainScreenGraph(
    navController: NavHostController,
) {
    composable(ROUTE_MAIN_SCREEN) {
        MainScreen()
    }
}
