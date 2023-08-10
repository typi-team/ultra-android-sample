package com.ultra.sample.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ultra.sample.R

internal val DefaultTypography = AppTypography(
    heading = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily(Font(R.font.inter_regular))
    ),
    title = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bottomItem = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    )
)

@Immutable
class AppTypography(
    val heading: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val bottomItem: TextStyle,
)

internal val LocalTypography = staticCompositionLocalOf<AppTypography> { error("No typography provided") }