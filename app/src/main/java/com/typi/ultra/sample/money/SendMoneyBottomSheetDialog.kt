package com.typi.ultra.sample.money

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.core.text.isDigitsOnly
import com.typi.ultra.R
import com.typi.ultra.sample.theme.AppTheme
import com.typi.ultra.transaction.model.Money
import com.typi.ultra.transaction.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SendMoneyBottomSheetDialog(
    onMoneySent: (Transaction) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    var input by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Отправить",
            color = AppTheme.colors.heading,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Списать с карты",
            color = AppTheme.colors.body,
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
                tint = AppTheme.colors.accent
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f, true)
            ) {
                Text(
                    text = "Мультивалютная",
                    color = AppTheme.colors.heading,
                    style = AppTheme.typography.heading,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "5500 13 •••• 0088",
                    color = AppTheme.colors.body,
                    style = AppTheme.typography.body,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = "27 016.19 ₸",
                color = AppTheme.colors.body,
                style = AppTheme.typography.body
            )
            Icon(
                painter = painterResource(R.drawable.ultra_ic_arrow_right),
                contentDescription = null,
                tint = AppTheme.colors.accent
            )
        }
        Text(
            text = "Сумма перевода",
            color = AppTheme.colors.body,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(top = 52.dp)
        )
        BasicTextField(
            value = input,
            onValueChange = {
                if (it.isDigitsOnly() && it.length < 13) {
                    input = it
                }
            },
            modifier = Modifier.focusRequester(focusRequester),
            textStyle = AppTheme.typography.title.copy(
                color = AppTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(AppTheme.colors.accent),
            singleLine = true
        ) { innerTextField ->
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    innerTextField()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = AppTheme.colors.accent)
                ) {
                }
            }
        }
        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    loading = true
                    val money = Money(
                        currencyCode = "KZT",
                        units = input.toLong(),
                        nanos = 0
                    )
                    val transaction = TransferMockService.sendMoney(money)
                    transaction?.let(onMoneySent)
                    loading = false
                }
            },
            modifier = Modifier
                .padding(top = 52.dp)
                .fillMaxWidth()
                .height(56.dp),
            enabled = input.isNotEmpty() && !loading,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.accent,
                disabledBackgroundColor = AppTheme.colors.disabled
            )
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = AppTheme.colors.onAccent
                )
            } else {
                Text(
                    text = stringResource(R.string.ultra_continue_text),
                    color = AppTheme.colors.onAccent,
                    style = AppTheme.typography.title
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}