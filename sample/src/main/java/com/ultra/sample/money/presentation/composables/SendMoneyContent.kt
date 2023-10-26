package com.ultra.sample.money.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.typi.ultra.R
import com.ultra.sample.money.presentation.SendMoneyState
import com.ultra.sample.theme.AppTheme

@Composable
fun SendMoneyContent(
    state: SendMoneyState,
    focusRequester: FocusRequester,
    onInputChange: (String) -> Unit,
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Отправить",
            color = AppTheme.colors.text.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Списать с карты",
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(top = 24.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ultra_ic_card),
                contentDescription = null,
                modifier = Modifier.padding(start = 4.dp),
                tint = AppTheme.colors.primary
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f, true)
            ) {
                Text(
                    text = "Мультивалютная",
                    color = AppTheme.colors.text.title,
                    style = AppTheme.typography.heading,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "5500 13 •••• 0088",
                    color = AppTheme.colors.text.subtitle,
                    style = AppTheme.typography.body,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = "27 016.19 ₸",
                color = AppTheme.colors.text.subtitle,
                style = AppTheme.typography.body
            )
            Icon(
                painter = painterResource(R.drawable.ultra_ic_arrow_right),
                contentDescription = null,
                tint = AppTheme.colors.primary
            )
        }
        Text(
            text = "Сумма перевода",
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(top = 52.dp)
        )
        BasicTextField(
            value = state.inputAmount,
            onValueChange = onInputChange,
            modifier = Modifier.focusRequester(focusRequester),
            textStyle = AppTheme.typography.title.copy(
                color = AppTheme.colors.text.content,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(AppTheme.colors.primary),
            singleLine = true
        ) { InnerTextField ->
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    InnerTextField()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = AppTheme.colors.primary)
                )
            }
        }
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .padding(top = 52.dp)
                .fillMaxWidth()
                .height(56.dp),
            enabled = state.isButtonEnabled,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.button.enabled,
                disabledBackgroundColor = AppTheme.colors.button.disabled
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = AppTheme.colors.text.button)
            } else {
                Text(
                    text = stringResource(R.string.ultra_continue_text),
                    color = AppTheme.colors.text.button,
                    style = AppTheme.typography.title
                )
            }
        }
    }
}