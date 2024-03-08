package com.swapi.tmdb.mainscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.swapi.search.searchScreenGraph
import com.swapi.tmdb.launchpad.ROUTE_LAUNCHPAD
import com.swapi.tmdb.launchpad.launchpadScreenGraph


@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    navigateToDetail: (Int) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_LAUNCHPAD
    ) {
        launchpadScreenGraph(
            navigateToDetail = navigateToDetail
        )

        searchScreenGraph(
            navigateToDetail = navigateToDetail
        )
    }
}
