package com.ultra.sample.ultra.delegates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.typi.ultra.integration.theme.UltraThemeDelegate
import com.typi.ultra.uikit.theme.DefaultUltraDarkColors
import com.typi.ultra.uikit.theme.DefaultUltraDarkIcons
import com.typi.ultra.uikit.theme.DefaultUltraLightColors
import com.typi.ultra.uikit.theme.DefaultUltraLightIcons
import com.typi.ultra.uikit.theme.DefaultUltraShape
import com.typi.ultra.uikit.theme.DefaultUltraTypography
import com.typi.ultra.uikit.theme.UltraColors
import com.typi.ultra.uikit.theme.UltraIcons
import com.typi.ultra.uikit.theme.UltraShape
import com.typi.ultra.uikit.theme.UltraTypography
import com.ultra.sample.core.settings.SettingsManager

class UltraThemeDelegateImpl(
    settingsManager: SettingsManager,
) : UltraThemeDelegate {

    private val _isDarkTheme = mutableStateOf(settingsManager.isDarkTheme)
    override val isDarkTheme: State<Boolean> = _isDarkTheme

    private val _lightColors = mutableStateOf(DefaultUltraLightColors())
    override val lightColors: State<UltraColors> = _lightColors

    private val _darkColors = mutableStateOf(DefaultUltraDarkColors())
    override val darkColors: State<UltraColors> = _darkColors

    private val _lightIcons = mutableStateOf(DefaultUltraLightIcons())
    override val lightIcons: State<UltraIcons> = _lightIcons

    private val _darkIcons = mutableStateOf(DefaultUltraDarkIcons())
    override val darkIcons: State<UltraIcons> = _darkIcons

    private val _typography = mutableStateOf(DefaultUltraTypography())
    override val typography: State<UltraTypography> = _typography

    private val _shapes = mutableStateOf(DefaultUltraShape())
    override val shapes: State<UltraShape> = _shapes

    override fun setDarkMode(useDarkTheme: Boolean) {
        _isDarkTheme.value = useDarkTheme
    }
}