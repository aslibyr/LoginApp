package com.app.loginapp.ui.ui.login.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.app.loginapp.ui.components.CustomTextField


@Composable
fun LoginScreenEmailContent(
    modifier: Modifier = Modifier,
    email: String,
    returnEmail: (String) -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = "Email",
            text = email,
            returnText = { input ->
                returnEmail.invoke(input)
            },
            onImeClicked = {},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
//            errorMessage = if (text.value.isEmpty() && isFocused) "Bu alan boş bırakılamaz" else null
        )
    }
}