package com.ultra.sample.reception.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.ultra.sample.R
import com.ultra.sample.theme.AppTheme

@Composable
fun ReceptionInput(
    onChangeClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var receptionState by remember { mutableStateOf("") }
    Row(
        modifier = modifier
    ) {
        BasicTextField(
            value = receptionState,
            onValueChange = {
                if (it.isDigitsOnly()) {
                    receptionState = it
                }
            },
            modifier = Modifier
                .width(220.dp)
                .height(48.dp),
            textStyle = AppTheme.typography.title,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
        ) { innerTextField ->
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (receptionState.isEmpty()) {
                    Text(
                        text = stringResource(R.string.enter_reception_number),
                        color = AppTheme.colors.text.subtitle,
                        style = AppTheme.typography.title,
                    )
                }
                innerTextField()
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                onChangeClicked(receptionState)
            },
            modifier = Modifier.height(48.dp),
            enabled = receptionState.isNotEmpty(),
        ) {
            Text(text = stringResource(R.string.ok))
        }
    }
}