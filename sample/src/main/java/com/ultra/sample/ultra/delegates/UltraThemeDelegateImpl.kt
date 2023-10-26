package com.ultra.sample.ultra.delegates

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.typi.ultra.integration.theme.UltraThemeDelegate
import com.typi.ultra.uikit.theme.UltraColor
import com.typi.ultra.uikit.theme.UltraShape
import com.typi.ultra.uikit.theme.UltraTypography
import com.ultra.sample.core.settings.SettingsManager

class UltraThemeDelegateImpl(
    settingsManager: SettingsManager,
) : UltraThemeDelegate {

    private val _isDarkTheme = mutableStateOf(settingsManager.isDarkTheme)
    override val isDarkTheme: State<Boolean> = _isDarkTheme

    private val _lightColors = mutableStateOf(
        UltraColor(
            primary = Color(0xFFE5E7EB),
            primaryVariant = Color(0xFFE5E7EB),
            accent = Color(0xFF22C55E),
            accentVariant = Color(0xFFDCFCE7),
            background = Color(0xFFF3F4F6),
            surface = Color.White,
            surfaceVariant = Color(0xFFE5E7EB),
            divider = Color(0xFFE5E7EB),
            disabled = Color(0xFFB7BCC5),
            error = Color(0xFFEF4444),
            onAccent = Color.White,
            onSurface = Color.Black,
            onDisabled = Color.White,
            heading = Color(0xFF374151),
            title = Color(0xFF374151),
            body = Color(0xFF6B7280),
            label = Color(0xFF9CA3AF),
            hint = Color(0xFF9CA3AF),
        )
    )
    override val lightColors: State<UltraColor> = _lightColors

    private val _darkColors = mutableStateOf(
        UltraColor(
            primary = Color(0xFF0B0F19),
            primaryVariant = Color(0xFF0B0F19),
            accent = Color(0xFF22C55E),
            accentVariant = Color(0xFFDCFCE7),
            background = Color(0xFF111827),
            surface = Color(0xFF1F2937),
            surfaceVariant = Color(0xFF1F2937),
            divider = Color(0xFF374151),
            disabled = Color(0xFF374151),
            error = Color(0xFFEF4444),
            onAccent = Color.White,
            onSurface = Color.White,
            onDisabled = Color.White,
            heading = Color(0xFFF3F4F6),
            title = Color(0xFFF3F4F6),
            body = Color(0xFF6B7280),
            label = Color(0xFF4B5563),
            hint = Color(0xFF374151),
        )
    )
    override val darkColors: State<UltraColor> = _darkColors

    private val _typography = mutableStateOf(
        UltraTypography(
            heading = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
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
    )
    override val typography: State<UltraTypography> = _typography

    private val _shapes = mutableStateOf(
        UltraShape(
            bottomSheet = RoundedCornerShape(16.dp)
        )
    )
    override val shapes: State<UltraShape> = _shapes

    override fun setDarkMode(useDarkTheme: Boolean) {
        _isDarkTheme.value = useDarkTheme
    }
}