package com.swapi.designsystem.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.swapi.designsystem.theme.Dimen24
import com.swapi.designsystem.theme.MoviesTheme
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.tmdb.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSTopBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    showNavigationIcon: Boolean? = false,
    showActionIcon: Boolean? = true,
    title: String,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets(0),
        navigationIcon = {
            if (showNavigationIcon == true) {
                IconButton(modifier = Modifier.size(Dimen24), onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "back"
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                style = TMDBTypography.headlineMedium,
            )
        }, actions = {
            if (showActionIcon == true) {
                IconButton(modifier = Modifier.size(Dimen24), onClick = onSearchClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search"
                    )
                }
            }
        })
}

@Composable
@ThemePreviews
fun DSTopBarPreview() {
    MoviesTheme {
        DSTopBar(
            title = "test",
            showNavigationIcon = false,
            onBackClick = {},
            onSearchClick = {},
        )
    }
}
