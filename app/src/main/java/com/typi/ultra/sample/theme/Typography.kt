package com.typi.ultra.sample.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val DefaultTypography = AppTypography(
    heading = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    title = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    label = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
)

@Immutable
class AppTypography(
    val heading: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
)

internal val LocalTypography = staticCompositionLocalOf<AppTypography> { error("No typography provided") }