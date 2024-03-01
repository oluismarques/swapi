package com.swapi.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = DSGray90,
    onPrimary = DSGray90,
    primaryContainer = DSGray70,
    onPrimaryContainer = DSGray90,
    secondary = DSGray90,
    onSecondary = DSGray90,
    secondaryContainer = DSGray90,
    onSecondaryContainer = DSGray90,
    tertiary = DSGray90,
    onTertiary = DSGray90,
    tertiaryContainer = DSGray90,
    onTertiaryContainer = DSGray90,
    error = DSRed,
    onError = DSRed,
    errorContainer = DSRed,
    onErrorContainer = DSRed,
    background = DSGray90,
    onBackground = DSGray90,
    surface = DSGray90,
    onSurface = DSGray90,
    surfaceVariant = DSGray90,
    onSurfaceVariant = DSGray90,
    inverseSurface = DSGray90,
    inverseOnSurface = DSGray90,
    outline = DSGray70,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = DSGray90,
    onPrimary = DSGray90,
    primaryContainer = DSGray70,
    onPrimaryContainer = DSGray90,
    secondary = DSGray90,
    onSecondary = DSGray90,
    secondaryContainer = DSGray90,
    onSecondaryContainer = DSGray90,
    tertiary = DSGray90,
    onTertiary = DSGray90,
    tertiaryContainer = DSGray90,
    onTertiaryContainer = DSGray90,
    error = DSRed,
    onError = DSRed,
    errorContainer = DSRed,
    onErrorContainer = DSRed,
    background = DSGray90,
    onBackground = DSGray90,
    surface = DSGray90,
    onSurface = DSGray90,
    surfaceVariant = DSGray90,
    onSurfaceVariant = DSGray90,
    inverseSurface = DSGray90,
    inverseOnSurface = DSGray90,
    outline = DSGray70,
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = DSGray70,
    onPrimary = DSWhite,
    primaryContainer = DSGray70,
    onPrimaryContainer = DSGray70,
    secondary = DSGray70,
    onSecondary = Color.White,
    secondaryContainer = DSGray70,
    onSecondaryContainer = DSGray70,
    tertiary = DSGray70,
    onTertiary = Color.White,
    tertiaryContainer = DSGray70,
    onTertiaryContainer = DSGray70,
    error = DSRed,
    onError = DSRed,
    errorContainer = DSRed,
    onErrorContainer = DSRed,
    background = DSGray70,
    onBackground = DSGray70,
    surface = DSGray70,
    onSurface = DSGray70,
    surfaceVariant = DSGray70,
    onSurfaceVariant = DSGray70,
    inverseSurface = DSGray70,
    inverseOnSurface = DSGray70,
    outline = DSGray70,
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = DSGray90,
    onPrimary = DSGray90,
    primaryContainer = DSGray70,
    onPrimaryContainer = DSGray90,
    secondary = DSGray90,
    onSecondary = DSGray90,
    secondaryContainer = DSGray90,
    onSecondaryContainer = DSGray90,
    tertiary = DSGray90,
    onTertiary = DSGray90,
    tertiaryContainer = DSGray90,
    onTertiaryContainer = DSGray90,
    error = DSRed,
    onError = DSRed,
    errorContainer = DSRed,
    onErrorContainer = DSRed,
    background = DSGray90,
    onBackground = DSGray90,
    surface = DSGray90,
    onSurface = DSGray90,
    surfaceVariant = DSGray90,
    onSurfaceVariant = DSGray90,
    inverseSurface = DSGray90,
    inverseOnSurface = DSGray90,
    outline = DSGray70,
)

/**
 * Light Android gradient colors
 */
val LightAndroidGradientColors = GradientColors(container = DSGray70)

/**
 * Dark Android gradient colors
 */
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DSGray70)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
fun StarWarsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }
    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )
    val gradientColors = when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }
    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = StarWarsTypography,
            content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
