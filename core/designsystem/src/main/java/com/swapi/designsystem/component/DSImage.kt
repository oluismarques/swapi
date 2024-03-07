package com.swapi.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.SubcomposeAsyncImageScope

@Composable
fun DSImage(
    modifier: Modifier = Modifier,
    model: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = model,
        contentScale = contentScale
    ) { SubcomposeAsyncImageHandler() }

}

@Composable
fun DSCardImage(
    modifier: Modifier = Modifier,
    model: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RectangleShape,
) {
    Card(modifier = modifier, shape = shape) {
        DSImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentScale = contentScale
        )
    }
}

@Composable
private fun SubcomposeAsyncImageScope.SubcomposeAsyncImageHandler() {
    when (painter.state) {
        is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
        is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        AsyncImagePainter.State.Empty, is AsyncImagePainter.State.Error -> Box(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@ThemePreviews
@Composable
private fun DSImagePreview() {
    DSImage(
        model = null,
    )
}
