package com.ultra.sample.core.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ultra.sample.theme.AppTheme

@Composable
fun ImagePlaceholder(
    modifier: Modifier = Modifier,
    initials: String,
    textSize: TextUnit = 12.sp,
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = AppTheme.colors.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = AppTheme.colors.primary,
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
        )
    }
}