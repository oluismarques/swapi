package com.swapi.starwars.mainscreen

import androidx.compose.foundation.background
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
import com.swapi.designsystem.theme.DSBackgroundLight
import com.swapi.designsystem.theme.StarWarsTypography

const val ROUTE_DRAGONS = "ROUTE_DRAGONS"
const val ROUTE_LAUNCHPAD = "ROUTE_LAUNCHPAD"

@Composable
fun MainScreenNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_LAUNCHPAD
    ) {
        composable(route = ROUTE_LAUNCHPAD) {
            DummyScreen(text = "Films")
        }

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
            .background(DSBackgroundLight)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = text,
            style = StarWarsTypography.headlineLarge,
            textAlign = TextAlign.Center,
        )
    }
}
