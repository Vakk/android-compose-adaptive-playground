package com.valeriik.adaptivedesign.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun AdaptiveComponent(
    mobile: @Composable () -> Unit,
    mobileLandscape: @Composable () -> Unit = mobile,
    mobileLarge: @Composable () -> Unit = mobile,
    mobileLargeLandscape: @Composable () -> Unit = mobileLarge,
    tablet: @Composable () -> Unit = mobileLarge,
    tabletLandscape: @Composable () -> Unit = tablet,
    tabletLarge: @Composable () -> Unit = tablet,
    tabletLargeLandscape: @Composable () -> Unit = tabletLandscape,
) {
    val configuration = LocalConfiguration.current

    val isPortrait = remember { configuration.orientation == Configuration.ORIENTATION_PORTRAIT }

    val shortestEdgeDp = remember {
        if (isPortrait) {
            configuration.orientation.dp
        } else {
            configuration.screenHeightDp.dp
        }
    }

    when {
        shortestEdgeDp < MIN_MOBILE_LARGE_DP.dp -> if (isPortrait) {
            mobile()
        } else {
            mobileLandscape()
        }

        shortestEdgeDp in MIN_MOBILE_LARGE_DP.dp..<MIN_TABLET_DP.dp -> if (isPortrait) {
            mobileLarge()
        } else {
            mobileLargeLandscape()
        }

        shortestEdgeDp in MIN_TABLET_DP.dp..<MIN_TABLET_LARGE_DP.dp -> if (isPortrait) {
            tablet()
        } else {
            tabletLandscape()
        }

        else -> if (isPortrait) {
            tabletLarge()
        } else {
            tabletLargeLandscape()
        }
    }
}

private const val MIN_MOBILE_LARGE_DP = 600
private const val MIN_TABLET_DP = 700
private const val MIN_TABLET_LARGE_DP = 960