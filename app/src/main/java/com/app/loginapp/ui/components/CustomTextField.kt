package com.app.loginapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomPasswordTextFieldWithLabel(
    modifier: Modifier,
    label: String,
    text: String,
    returnText: (String) -> Unit,
    onImeClicked: () -> Unit,
    keyboardOptions: KeyboardOptions,
    maxLength: Int? = null,
    errorMessage: String? = null
) {

    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }
    val passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = text,
            onValueChange = { output ->
                if (maxLength != null) {
                    if (output.length <= maxLength) returnText(output)
                } else {
                    returnText(output)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.hasFocus
                },
//            trailingIcon = {
//                val image = if (passwordVisible)
//                    Icons.Outlined.HideSource else Icons.Filled.HideSource
//
//                val description = if (passwordVisible) "Hide password" else "Show password"
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(imageVector = image, description)
//                }
//            },
            isError = !errorMessage.isNullOrEmpty(),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeClicked()
                },
                onGo = {
                    onImeClicked()
                }
            ),
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = lightGreyBorderColor,
//                unfocusedIndicatorColor = lightGreyBorderColor,
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedLabelColor = MaterialTheme.colorScheme.secondary,
//                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
//                cursorColor = primaryPurpleColor,
//                errorContainerColor = Color.White,
//                errorIndicatorColor = errorRed
//
//            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            )
        errorMessage?.let { it1 ->
            Text(
                text = it1,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier,
    label: String,
    text: String,
    returnText: (String) -> Unit,
    onImeClicked: () -> Unit,
    keyboardOptions: KeyboardOptions,
    maxLength: Int? = null,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    val isError = !errorMessage.isNullOrEmpty() && !isFocused

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { output ->
                if (maxLength != null) {
                    if (output.length <= maxLength) returnText(output)
                } else {
                    returnText(output)
                }
            },
            label = {
                Text(
                    text = label,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.hasFocus
                },
            trailingIcon = {},
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeClicked()
                },
                onGo = {
                    onImeClicked()
                }
            ),
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = lightGreyBorderColor,
//                unfocusedIndicatorColor = lightGreyBorderColor,
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedLabelColor = MaterialTheme.colorScheme.secondary,
//                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
//                cursorColor = primaryPurpleColor,
//                errorContainerColor = Color.White,
//                errorIndicatorColor = errorRed
//
//            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = visualTransformation,
            supportingText = {
                errorMessage?.let { it1 ->
                    Text(
                        text = it1,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }
            }
        )
    }
}