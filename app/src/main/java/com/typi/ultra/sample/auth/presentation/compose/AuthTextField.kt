package com.typi.ultra.sample.auth.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.typi.ultra.sample.theme.AppTheme

@Composable
fun AuthTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = AppTheme.typography.title,
        keyboardOptions = keyboardOptions,
        singleLine = true,
    ) { innerTextField ->
        Surface(
            color = Color.White,
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = AppTheme.colors.body,
                        style = AppTheme.typography.title
                    )
                }
                innerTextField()
            }
        }
    }
}