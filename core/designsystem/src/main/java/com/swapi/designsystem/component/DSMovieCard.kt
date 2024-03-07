package com.swapi.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swapi.designsystem.theme.Dimen4
import com.swapi.designsystem.theme.Dimen8
import com.swapi.designsystem.theme.MoviesTheme
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.designsystem.theme.TextSmall

@Composable
fun DSMovieCard(
    modifier: Modifier = Modifier,
    name: String,
    voteAverage: String,
    releaseDate: String,
    onCardClick: () -> Unit,
    imageUrl: String?,
    itemWidth: Dp = 120.dp,
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .clickable(onClick = { onCardClick() }),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.width(itemWidth),
                contentScale = ContentScale.Crop
            )
            DSRatingItem(
                rating = voteAverage,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Dimen4)
            )
        }

        Column(
            modifier = Modifier.width(itemWidth).padding(Dimen8),
            verticalArrangement = Arrangement.spacedBy(Dimen8)
        ) {
            Text(
                text = name,
                style = TMDBTypography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .width(itemWidth)
                    .wrapContentHeight()
            )

            Text(
                text = releaseDate,
                fontSize = TextSmall,
                style = TMDBTypography.labelSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .width(itemWidth)
                    .wrapContentHeight()
            )
        }
    }
}

@ThemePreviews
@Composable
private fun DSMovieCardPreview() {
    MoviesTheme {
        DSMovieCard(
            modifier = Modifier.height(140.dp),
            name = "Suzie",
            onCardClick = { -> },
            imageUrl = null,
            itemWidth = 120.dp,
            voteAverage = "2.0",
            releaseDate = "sds"
        )
    }
}