package com.swapi.tmdb.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.tmdb.feature.detail.detailScreenGraph
import com.swapi.tmdb.launchpad.ROUTE_LAUNCHPAD
import com.swapi.tmdb.launchpad.launchpadScreenGraph

const val ROUTE_DRAGONS = "ROUTE_DRAGONS"

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

        composable(route = ROUTE_DRAGONS) {
            DummyScreen(text = "Dragons")
        }
    }
}

@Composable
private fun DummyScreen(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = text,
            style = TMDBTypography.headlineLarge,
            textAlign = TextAlign.Center,
        )
    }
}
