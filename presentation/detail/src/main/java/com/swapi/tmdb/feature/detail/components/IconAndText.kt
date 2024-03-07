package com.swapi.tmdb.feature.detail.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.swapi.designsystem.theme.Dimen20
import com.swapi.designsystem.theme.Dimen8
import com.swapi.designsystem.theme.TMDBTypography

@Composable
internal fun IconAndText(
    modifier: Modifier = Modifier,
    @DrawableRes iconResourceId: Int,
    text: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimen8)
    ) {
        Icon(
            modifier = Modifier.size(Dimen20),
            painter = painterResource(id = iconResourceId),
            contentDescription = text,
        )
        Text(
            text = text,
            style = TMDBTypography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

