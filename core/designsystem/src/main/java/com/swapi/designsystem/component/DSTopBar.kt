package com.swapi.designsystem.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.swapi.designsystem.theme.DSBackgroundLight
import com.swapi.designsystem.theme.Dimen16
import com.swapi.designsystem.theme.Dimen24
import com.swapi.designsystem.theme.StarWarsTypography
import com.swapi.starwars.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSTopBar(
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    showNavigationIcon: Boolean? = false,
    showActionIcon: Boolean? = true,
    title: String,
) {
    CenterAlignedTopAppBar(
        modifier = modifier.padding(horizontal = Dimen16),
        windowInsets = WindowInsets(0),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = DSBackgroundLight),
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
                style = StarWarsTypography.headlineMedium,
            )
        }, actions = {
            if (showActionIcon == true) {
                IconButton(modifier = Modifier.size(Dimen24), onClick = onFilterClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "filter"
                    )
                }
            }
        })
}

@Composable
@Preview
fun DSTopBarPreview() {
    DSTopBar(
        title = "test",
        showNavigationIcon = false,
        onBackClick = {},
        onFilterClick = {},
    )
}
