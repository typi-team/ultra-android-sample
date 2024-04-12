package com.ultra.sample.auth.presentation.composeable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ultra.sample.R
import com.ultra.sample.auth.presentation.LoginUiState
import com.ultra.sample.theme.AppTheme
import java.util.regex.Pattern

internal const val phoneLength = 12

@Composable
fun LoginScreen(
    viewState: LoginUiState,
    onAuthButtonClicked: () -> Unit,
    onLoginButtonClicked: (String, String, String, String?) -> Unit,
) {
    when (viewState) {
        is LoginUiState.Auth -> AuthContent(
            viewState = viewState,
            onAuthButtonClicked = onAuthButtonClicked
        )
        is LoginUiState.UnAuth -> UnAuthContent(
            viewState = viewState,
            onLoginButtonClicked = onLoginButtonClicked
        )
    }
}

@Composable
private fun AuthContent(
    viewState: LoginUiState.Auth,
    onAuthButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background.general)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF22C55E),
                disabledBackgroundColor = Color(0xFF22C55E).copy(alpha = 0.25f)
            ),
            onClick = onAuthButtonClicked,
        ) {
            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = stringResource(R.string.auth),
                    color = Color.White,
                    style = AppTheme.typography.title
                )
            }
        }
    }
}

@Composable
private fun UnAuthContent(
    viewState: LoginUiState.UnAuth,
    onLoginButtonClicked: (String, String, String, String?) -> Unit,
) {
    var nicknameState by remember { mutableStateOf("") }
    var phoneState by remember { mutableStateOf("") }
    var firstnameState by remember { mutableStateOf("") }
    var lastnameState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background.general)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
        )
        Image(
            painter = painterResource(R.drawable.logo_reg),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 32.dp),
            text = stringResource(R.string.auth_enter_data),
            color = AppTheme.colors.text.title,
            style = AppTheme.typography.title,
            textAlign = TextAlign.Center
        )
        AuthTextField(
            value = nicknameState,
            hint = stringResource(R.string.your_nickname),
            onValueChange = {
                if (it.isEmpty() || it.validateNickname()) nicknameState = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Text(
            text = stringResource(R.string.sample_nickname),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body
        )
        AuthTextField(
            value = phoneState,
            hint = stringResource(R.string.your_phone_number),
            onValueChange = { value ->
                val newValue = value.filter { it.isDigit() || it == '+' }
                if (newValue.length <= phoneLength) phoneState = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )
        Text(
            text = stringResource(R.string.sample_phone_number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body
        )
        AuthTextField(
            value = firstnameState,
            hint = stringResource(R.string.your_firstname),
            onValueChange = {
                if (it.isEmpty() || it.trim().isNotEmpty()) firstnameState = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )
        )
        Text(
            text = stringResource(R.string.sample_firstname),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body
        )
        AuthTextField(
            value = lastnameState,
            hint = stringResource(R.string.your_lastname),
            onValueChange = {
                if (it.isEmpty() || it.trim().isNotEmpty()) lastnameState = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            )
        )
        Text(
            text = stringResource(R.string.sample_lastname),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            color = AppTheme.colors.text.subtitle,
            style = AppTheme.typography.body
        )
        Button(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF22C55E),
                disabledBackgroundColor = Color(0xFF22C55E).copy(alpha = 0.25f)
            ),
            enabled = nicknameState.isNotEmpty() && phoneState.length >= phoneLength && firstnameState.isNotEmpty(),
            onClick = {
                onLoginButtonClicked.invoke(
                    nicknameState.trim(),
                    phoneState,
                    firstnameState.trim(),
                    lastnameState.trim()
                )
            }
        ) {
            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = stringResource(R.string.continue_text),
                    color = Color.White,
                    style = AppTheme.typography.title
                )
            }
        }
    }
}

fun String.validateNickname(): Boolean {
    if (trim().isEmpty()) return false
    return Pattern.compile("^[A-Za-z0-9_.]+\$").matcher(this).matches()
}

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        UnAuthContent(
            viewState = LoginUiState.UnAuth(),
            onLoginButtonClicked = { _, _, _, _ -> }
        )
    }
}