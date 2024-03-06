package com.swapi.tmdb.feature.detail.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swapi.designsystem.component.DSCardImage
import com.swapi.designsystem.theme.Dimen12
import com.swapi.designsystem.theme.Dimen24
import com.swapi.designsystem.theme.Dimen8
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.tmdb.domain.detail.Credits
import com.swapi.tmdb.feature.detail.R

@Composable
internal fun CastAndCrew(
    modifier: Modifier = Modifier,
    credits: Credits,
) {
    val cast = credits.cast
    val crew = credits.crew

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimen24)
    ) {
        if (cast.isNotEmpty()) {
            CastAndCrewContainer(titleResourceId = R.string.cast) {
                items(cast) { castItem ->
                    with(castItem) {
                        CastAndCrewItem(
                            profilePath = profilePath,
                            name = name,
                            description = character
                        )
                    }
                }
            }
        }
        if (crew.isNotEmpty()) {
            CastAndCrewContainer(titleResourceId = R.string.crew) {
                items(crew) { crewItem ->
                    with(crewItem) {
                        CastAndCrewItem(
                            profilePath = profilePath,
                            name = name,
                            description = job
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CastAndCrewContainer(
    @StringRes titleResourceId: Int,
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimen24)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = Dimen12),
            text = stringResource(id = titleResourceId),
            style = TMDBTypography.titleMedium,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimen12),
            contentPadding = PaddingValues(horizontal = Dimen12),
            content = content
        )
    }
}

@Composable
private fun CastAndCrewItem(
    profilePath: String?,
    name: String,
    description: String,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    isPlaceholder: Boolean = false,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimen12)
    ) {
        if (isPlaceholder) {
            Box(
                modifier = Modifier
                    .size(CastAndCrewItemImageSize)
                    .clip(shape)
            )
        } else {
            DSCardImage(
                modifier = Modifier.size(CastAndCrewItemImageSize),
                model = profilePath,
                shape = shape
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(Dimen8)) {
            Text(
                modifier = if (isPlaceholder) {
                    Modifier
                        .width(PlaceholderTextWidth)
                } else {
                    Modifier
                },
                text = name,
                style = TMDBTypography.titleSmall,
            )
            Text(
                modifier = if (isPlaceholder) {
                    Modifier
                        .width(PlaceholderTextWidth)

                } else {
                    Modifier
                },
                text = description,
                style = TMDBTypography.titleSmall,
            )
        }
    }
}

@Composable
private fun CastAndCrewItemPlaceholder(modifier: Modifier = Modifier) {
    CastAndCrewItem(
        modifier = modifier,
        profilePath = null,
        name = PlaceholderText,
        description = PlaceholderText,
        isPlaceholder = true
    )
}

private val CastAndCrewItemImageSize = 40.dp
private val PlaceholderTextWidth = 50.dp
private const val PlaceholderText = ""
private const val PlaceholderCount = 20
