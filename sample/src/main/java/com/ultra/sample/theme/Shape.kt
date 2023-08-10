package com.ultra.sample.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val DefaultShape = AppShape(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Immutable
class AppShape(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
)

internal val LocalShapes = staticCompositionLocalOf<AppShape> { error("No shapes provided") }