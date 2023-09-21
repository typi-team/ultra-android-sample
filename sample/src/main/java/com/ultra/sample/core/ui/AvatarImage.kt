package com.ultra.sample.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ultra.sample.theme.AppTheme

@Composable
internal fun AvatarImage(
    modifier: Modifier = Modifier,
    url: String?,
    placeholder:String,
    shape: Shape = CircleShape,
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .size(40.dp)
            .fillMaxSize()
            .clip(shape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> CircularProgressIndicator(color = AppTheme.colors.primary)
            is AsyncImagePainter.State.Error -> ImagePlaceholder(
                modifier = modifier,
                initials = placeholder
            )
            else -> SubcomposeAsyncImageContent()
        }
    }
}