package com.ultra.sample.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LightPalette = AppColor(
    primary = Color(0xFF22C55E),
    background = Background(
        general = Color(0xFFF3F4F6),
        surface = Color.White
    ),
    text = Text(
        title = Color(0xFF374151),
        subtitle = Color(0xFF6B7280),
        content = Color.Black,
        button = Color.White
    ),
    icon = Icon(
        selected = Color(0xFF22C55E),
        disabled = Color(0xFF4B5563)
    ),
    button = Button(
        enabled = Color(0xFF16A34A),
        disabled = Color(0xFFB7BCC5),
    )
)

@Immutable
class AppColor(
    val primary: Color,
    val background: Background,
    val text: Text,
    val icon: Icon,
    val button: Button,
)

@Immutable
class Background(
    val general: Color,
    val surface: Color,
)

@Immutable
class Text(
    val title: Color,
    val subtitle: Color,
    val content: Color,
    val button: Color,
)

@Immutable
class Icon(
    val selected: Color,
    val disabled: Color,
)

class Button(
    val enabled: Color,
    val disabled: Color,
)

internal val LocalColors = staticCompositionLocalOf<AppColor> { error("No colors provided") }