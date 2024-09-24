package com.valeriik.adaptivedesign.ui.adaptive_component_usage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.valeriik.adaptivedesign.ui.components.AdaptiveComponent

@Composable
fun AdaptiveScreen(modifier: Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        val contentModifier = remember {
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        }
        AdaptiveComponent(
            mobile = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Mobile",
                        textAlign = TextAlign.Center
                    )
                }
            },
            mobileLandscape = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Mobile Landscape",
                        textAlign = TextAlign.Center
                    )
                }
            },
            mobileLarge = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Mobile Large",
                        textAlign = TextAlign.Center
                    )
                }
            },

            mobileLargeLandscape = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Mobile Large Landscape",
                        textAlign = TextAlign.Center
                    )
                }
            },
            tablet = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Tablet",
                        textAlign = TextAlign.Center
                    )
                }
            },
            tabletLandscape = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Tablet Landscape",
                        textAlign = TextAlign.Center
                    )
                }
            },
            tabletLarge = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Tablet Large",
                        textAlign = TextAlign.Center
                    )
                }
            },
            tabletLargeLandscape = {
                Box(modifier = contentModifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = "This is Tablet Large Landscape",
                        textAlign = TextAlign.Center
                    )
                }
            }
        )
    }
}