package com.typi.ultra.sample.ultra

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.typi.ultra.sample.core.settings.SettingsManager
import com.typi.ultra.sample.theme.Accent
import com.typi.ultra.sample.theme.AccentVariant
import com.typi.ultra.sample.theme.Error
import com.typi.ultra.sample.theme.Gray100
import com.typi.ultra.sample.theme.Gray1000
import com.typi.ultra.sample.theme.Gray200
import com.typi.ultra.sample.theme.Gray350
import com.typi.ultra.sample.theme.Gray400
import com.typi.ultra.sample.theme.Gray500
import com.typi.ultra.sample.theme.Gray600
import com.typi.ultra.sample.theme.Gray700
import com.typi.ultra.sample.theme.Gray800
import com.typi.ultra.sample.theme.Gray900
import com.typi.ultra.uikit.UltraThemeDelegate
import com.typi.ultra.uikit.theme.UltraColor
import com.typi.ultra.uikit.theme.UltraShape
import com.typi.ultra.uikit.theme.UltraTypography

class UltraThemeDelegateImpl(
    settingsManager: SettingsManager,
) : UltraThemeDelegate {

    private val _isDarkTheme = mutableStateOf(settingsManager.isDarkTheme)
    override val isDarkTheme: State<Boolean> = _isDarkTheme

    private val _lightColors = mutableStateOf(
        UltraColor(
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
    )
    override val lightColors: State<UltraColor> = _lightColors

    private val _darkColors = mutableStateOf(
        UltraColor(
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
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp)
        )
    )
    override val shapes: State<UltraShape> = _shapes

    override fun setDarkMode(useDarkTheme: Boolean) {
        _isDarkTheme.value = useDarkTheme
    }
}