package com.app.loginapp.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.loginapp.R
import com.app.loginapp.components.button.CustomButton
import com.app.loginapp.components.textfield.CustomTextField
import com.app.loginapp.theme.Purple
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    updateEmail: (String) -> Unit,
    password: String,
    updatePassword: (String) -> Unit,
    onLoginClicked: () -> Unit,
    mailError: String? = null,
    passwordError: String? = null
) {
    var isLoaded by remember {
        mutableStateOf(
            false
        )
    }

    LaunchedEffect(Unit) {
        if (!isLoaded) {
            delay(250L)
            isLoaded = true
        }

    }
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.illustration_login),
            contentDescription = null,
            modifier
                .fillMaxHeight(0.25f)
                .padding(horizontal = 32.dp)
                .padding(top = 32.dp),
        )
        Text(
            text = stringResource(R.string.login_title_text),
            fontWeight = FontWeight.W900,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        AnimatedVisibility(
            visible = isLoaded,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 800)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 800)
            )
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    label = stringResource(R.string.email),
                    text = email,
                    returnText = { input ->
                        updateEmail.invoke(input)
                    },
                    onImeClicked = {},
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    errorMessage = mailError,
                    leadingIcon = Icons.Default.Email
                )

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = stringResource(R.string.password),
                    text = password,
                    returnText = { input ->
                        updatePassword.invoke(input)
                    },
                    onImeClicked = { onLoginClicked.invoke() },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    errorMessage = passwordError,
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )

                CustomButton(modifier.padding(horizontal = 16.dp), onClick = {
                    onLoginClicked.invoke()
                }, buttonText = "Giriş yap")

                Row(
                    modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.no_account),
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )

                    Text(
                        text = stringResource(R.string.create_account),
                        color = Purple,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}