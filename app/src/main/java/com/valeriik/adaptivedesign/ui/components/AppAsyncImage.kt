package com.valeriik.adaptivedesign.ui.components

import android.content.Context
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun AppAsyncImage(
    context: Context = LocalContext.current,
    data: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(data)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = contentDescription
    )
}