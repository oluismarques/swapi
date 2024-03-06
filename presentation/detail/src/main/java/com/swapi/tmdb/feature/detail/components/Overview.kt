package com.swapi.tmdb.feature.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.swapi.designsystem.theme.Dimen16
import com.swapi.designsystem.theme.Dimen8
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.tmdb.feature.detail.R

@Composable
internal fun Overview(
    overview: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = Dimen16)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimen8)
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            style = TMDBTypography.titleMedium,
        )
        Text(
            text = overview.ifEmpty { stringResource(id = R.string.no_overview) },
            style = TMDBTypography.bodyMedium,
        )
    }
}
