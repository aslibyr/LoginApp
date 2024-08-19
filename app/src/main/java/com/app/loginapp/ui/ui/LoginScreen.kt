package com.app.loginapp.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.app.loginapp.ui.components.CustomButton
import com.app.loginapp.ui.components.CustomTextField

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val text = remember { mutableStateOf("") }

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Text",
            text = text.value,
            returnText = { input ->
                text.value = input
            },
            onImeClicked = {},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
//            errorMessage = if (text.value.isEmpty() && isFocused) "Bu alan boş bırakılamaz" else null
        )
        CustomButton(onClick = {}, buttonText = "Buton")
    }
}