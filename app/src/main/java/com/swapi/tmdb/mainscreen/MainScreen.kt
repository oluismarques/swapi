package com.swapi.tmdb.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.swapi.designsystem.extensions.NoRippleInteractionSource
import com.swapi.designsystem.extensions.NoRippleTheme
import com.swapi.designsystem.theme.DSGray10
import com.swapi.designsystem.theme.DSGray90
import com.swapi.designsystem.theme.Dimen1
import com.swapi.designsystem.theme.Dimen56
import com.swapi.tmdb.launchpad.ROUTE_LAUNCHPAD

data class BottomNavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
)

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainScreenNavHost(
                navController = navController,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = ROUTE_LAUNCHPAD,
        ),
        BottomNavItem(
            selectedIcon = Icons.Filled.Adb,
            unselectedIcon = Icons.Filled.Adb,
            route = ROUTE_DRAGONS,
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            HorizontalDivider(thickness = Dimen1, color = DSGray10)

            NavigationBar(
                modifier = Modifier.height(Dimen56)
            ) {
                items.forEach { bottomNavItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == bottomNavItem.route
                        } == true,
                        onClick = {
                            navController.navigate(bottomNavItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (currentDestination?.hierarchy?.any {
                                        it.route == bottomNavItem.route
                                    } == true) {
                                    bottomNavItem.selectedIcon
                                } else {
                                    bottomNavItem.unselectedIcon
                                },
                                contentDescription = bottomNavItem.selectedIcon.toString(),
                            )
                        },
                        interactionSource = NoRippleInteractionSource(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(navigateToDetail = {})
}
