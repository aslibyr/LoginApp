package com.app.loginapp.ui.ui.login.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.loginapp.ui.components.CustomTextField

@Composable
fun LoginScreenPasswordContent(
    modifier: Modifier = Modifier,
    password: String,
    returnPassword: (String) -> Unit,
    errorMessage: String? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = "Şifre",
            text = password,
            returnText = { returnPassword.invoke(it) },
            onImeClicked = { },
            keyboardOptions = KeyboardOptions(),
            errorMessage = errorMessage
        )
    }
}