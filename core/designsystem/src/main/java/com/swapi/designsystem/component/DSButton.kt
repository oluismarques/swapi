package com.swapi.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swapi.designsystem.theme.DSWhite
import com.swapi.designsystem.theme.StarWarsTypography

@Composable
fun DsButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
    ),
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        onClick = onClick
    ) {
        Text(
            text = text, style = StarWarsTypography.labelMedium.copy(color = DSWhite)
        )
    }
}


@Composable
@Preview
fun DsButtonPreview() {
    DsButton(onClick = {}, text = "Test button")
}
