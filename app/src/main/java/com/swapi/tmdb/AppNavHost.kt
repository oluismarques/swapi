package com.swapi.tmdb

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.swapi.tmdb.feature.detail.detailScreenGraph
import com.swapi.tmdb.feature.detail.navigateToDetail
import com.swapi.tmdb.mainscreen.ROUTE_MAIN_SCREEN
import com.swapi.tmdb.mainscreen.mainScreenGraph

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE_MAIN_SCREEN
    ) {
        mainScreenGraph(navigateToDetail = { navigateToDetail(navController, it) })

        detailScreenGraph(navController)
    }
}


private fun navigateToDetail(
    navController: NavHostController,
    id: Int,
) {
    navController.navigateToDetail(
        id = id,
        builder = {},
    )
}
