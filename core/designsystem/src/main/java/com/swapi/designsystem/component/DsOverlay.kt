package com.swapi.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

@Composable
fun DsOverlay(
    color: Color,
    alpha: Float,
    modifier: Modifier = Modifier,
    brush: Brush = Brush.verticalGradient(listOf(color.copy(alpha = alpha), color)),
    shape: Shape = RectangleShape,
) {
    Box(
        modifier = modifier
            .background(brush = brush, shape = shape)
            .fillMaxSize()
    )
}
