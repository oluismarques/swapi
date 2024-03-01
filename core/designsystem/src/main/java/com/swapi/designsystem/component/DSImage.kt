package com.swapi.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swapi.starwars.designsystem.R

@Composable
fun DSImage(
    @DrawableRes defaultPlaceholder: Int = R.drawable.ic_placeholder,
    imagePath: String? = null,
    imageSize: Dp = 40.dp,
) {
    Box(
        modifier = Modifier
            .size(imageSize),
    ) {
        AsyncImage(
            model = imagePath ?: defaultPlaceholder,
            error = painterResource(id = defaultPlaceholder),
            placeholder = painterResource(defaultPlaceholder),
            contentDescription = "avatarImage",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun DSImagePreview() {
    DSImage(
        imagePath = null,
        imageSize = 40.dp,
    )
}
