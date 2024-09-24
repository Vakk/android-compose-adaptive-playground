package com.valeriik.adaptivedesign.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF795548),       // Brown 500
    secondary = Color(0xFFFFA726),     // Orange 400
    background = Color(0xFFFBE9E7),    // Orange 50
    surface = Color(0xFFFFFFFF),       // White
    onPrimary = Color(0xFFFFFFFF),     // White text on primary
    onSecondary = Color(0xFF000000),   // Black text on secondary
    onBackground = Color(0xFF263238),  // Blue Gray text
    onSurface = Color(0xFF263238)      // Blue Gray text on surface
)

@Composable
fun AdaptiveDesignTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}