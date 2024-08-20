package com.app.loginapp.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: ImageVector? = null
) {

    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    val isError = !errorMessage.isNullOrEmpty() && !isFocused

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
        modifier = modifier
            .onFocusChanged {
                isFocused = it.hasFocus
            },
        leadingIcon = {
            leadingIcon?.let {
                Icon(imageVector = it, contentDescription = "")
            }
        },
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
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        shape = RoundedCornerShape(30.dp),
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