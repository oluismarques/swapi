package com.swapi.tmdb

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.swapi.tmdb.mainscreen.ROUTE_MAIN_SCREEN
import com.swapi.tmdb.mainscreen.mainScreenGraph

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE_MAIN_SCREEN
    ) {
        mainScreenGraph(navController = navController)
    }
}

