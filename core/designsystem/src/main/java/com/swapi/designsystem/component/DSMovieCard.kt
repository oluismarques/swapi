package com.swapi.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swapi.designsystem.theme.MoviesTheme
import com.swapi.designsystem.theme.TMDBTypography
import com.swapi.designsystem.theme.TextSmall

@Composable
fun DSMovieCard(
    modifier: Modifier = Modifier,
    name: String,
    onFeedClick: () -> Unit,
    imageUrl: String?,
    itemWidth: Dp = 120.dp,
) {
    Card(
        modifier = modifier
            .clickable(onClick = { onFeedClick() }),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.width(itemWidth),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                fontSize = TextSmall,
                style = TMDBTypography.labelMedium.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .size(width = itemWidth, height = 36.dp)
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
            name = "Suzie", onFeedClick = { -> }, imageUrl = null, itemWidth = 120.dp
        )
    }
}