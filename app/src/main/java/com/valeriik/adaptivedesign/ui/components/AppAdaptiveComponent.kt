package com.valeriik.adaptivedesign.ui.components

import androidx.compose.runtime.Composable

@Composable
fun AppAdaptiveComponent(
    mobile: @Composable () -> Unit,
    tablet: @Composable () -> Unit
) {
    AdaptiveComponent(
        mobile = mobile,
        mobileLandscape = mobile,
        tablet = tablet,
        tabletLandscape = tablet,
        tabletLarge = tablet,
        tabletLargeLandscape = tablet
    )
}