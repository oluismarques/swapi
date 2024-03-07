package com.swapi.tmdb.feature.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swapi.designsystem.component.DSCardImage
import com.swapi.designsystem.component.DSImage
import com.swapi.designsystem.component.DSRatingItem
import com.swapi.designsystem.component.DSTopBar
import com.swapi.designsystem.component.DsOverlay
import com.swapi.designsystem.theme.Dimen12
import com.swapi.designsystem.theme.Dimen24
import com.swapi.designsystem.theme.Dimen8
import com.swapi.tmdb.domain.detail.Credits
import com.swapi.tmdb.feature.detail.R

@Composable
internal fun DetailsItem(
    title: String,
    overview: String,
    posterPath: String?,
    releaseDate: String?,
    runtime: String,
    genres: List<String>,
    voteAverage: String,
    voteCount: String,
    credits: Credits,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets.safeDrawing.only(
        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
    ),
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(BackdropHeight)
        ) {

            DSImage(
                modifier = Modifier.fillMaxSize().testTag("posterPath_tag"),
                model = posterPath,
            )

            DsOverlay(color = MaterialTheme.colorScheme.surface, alpha = BackdropAlpha)
        }

        Scaffold(
            topBar = {
                DSTopBar(
                    modifier = Modifier.padding(horizontal = Dimen12),
                    windowInsets = windowInsets,
                    title = title,
                    actionIcon = com.swapi.tmdb.designsystem.R.drawable.ic_wishlist,
                    showNavigationIcon = true,
                    onBackClick = onBackButtonClick
                )
            }, containerColor = Color.Transparent, contentWindowInsets = windowInsets
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen24)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Dimen24)
                    ) {

                        DSCardImage(
                            modifier = Modifier.size(
                                width = PosterWidth, height = PosterHeight
                            ), model = posterPath, shape = RoundedCornerShape(Dimen12)
                        )

                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Dimen8)
                        ) {
                            IconAndText(
                                iconResourceId = R.drawable.ic_calendar,
                                text = releaseDate ?: stringResource(id = R.string.no_release_date)
                            )
                            IconAndText(
                                iconResourceId = R.drawable.ic_clock, text = stringResource(
                                    id = R.string.details_runtime_text, runtime
                                )
                            )
                            IconAndText(
                                iconResourceId = R.drawable.ic_clock, text = stringResource(
                                    id = R.string.details_vote_count, voteCount
                                )
                            )
                            IconAndText(iconResourceId = R.drawable.ic_film,
                                text = genres.joinToString(separator = GenreSeparator)
                                    .ifEmpty { stringResource(id = R.string.no_genre) })
                        }

                        DSRatingItem(rating = voteAverage)
                    }
                }
                item {
                    Overview(overview = overview)
                }
                item {
                    CastAndCrew(credits = credits)
                }
                item {
                    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                }
            }
        }
    }
}


private val BackdropHeight = 552.dp
private val PosterWidth = 205.dp
private val PosterHeight = 287.dp
private const val BackdropAlpha = 0.2f
private const val GenreSeparator = ", "
