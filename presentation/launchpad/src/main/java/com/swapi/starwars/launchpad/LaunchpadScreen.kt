package com.swapi.starwars.launchpad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.swapi.designsystem.component.DSTopBar
import com.swapi.designsystem.theme.DSBackgroundLight


@Composable
internal fun LaunchPadScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DSBackgroundLight),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DSTopBar(
            onFilterClick = { }, title = stringResource(R.string.launches_top_bar_title)
        )
    }
}

@Preview
@Composable
private fun LaunchPadScreenPreview() {
    LaunchPadScreen()
}
