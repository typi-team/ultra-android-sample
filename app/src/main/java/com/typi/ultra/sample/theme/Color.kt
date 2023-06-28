package com.typi.ultra.sample.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LightPalette = AppColor(
    primary = Gray200,
    primaryVariant = Gray200,
    accent = Accent,
    accentVariant = AccentVariant,
    background = Gray100,
    surface = Color.White,
    surfaceVariant = Gray200,
    divider = Gray200,
    disabled = Gray350,
    error = Error,
    onAccent = Color.White,
    onSurface = Color.Black,
    onDisabled = Color.White,
    heading = Gray700,
    title = Gray700,
    body = Gray500,
    label = Gray400,
    hint = Gray400,
)

internal val DarkPalette = AppColor(
    primary = Gray1000,
    primaryVariant = Gray1000,
    accent = Accent,
    accentVariant = AccentVariant,
    background = Gray900,
    surface = Gray800,
    surfaceVariant = Gray800,
    divider = Gray700,
    disabled = Gray700,
    error = Error,
    onAccent = Color.White,
    onSurface = Color.White,
    onDisabled = Color.White,
    heading = Gray100,
    title = Gray100,
    body = Gray500,
    label = Gray600,
    hint = Gray700,
)

@Immutable
class AppColor(
    val primary: Color,
    val primaryVariant: Color,
    val accent: Color,
    val accentVariant: Color,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val divider: Color,
    val disabled: Color,
    val error: Color,
    val onAccent: Color,
    val onSurface: Color,
    val onDisabled: Color,
    val heading: Color,
    val title: Color,
    val body: Color,
    val label: Color,
    val hint: Color,
)

internal val LocalColors = staticCompositionLocalOf<AppColor> { error("No colors provided") }