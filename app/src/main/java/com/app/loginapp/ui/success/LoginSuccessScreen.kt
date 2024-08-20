package com.app.loginapp.ui.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.loginapp.components.button.CustomButton

@Composable
fun LoginSuccessScreen(modifier: Modifier = Modifier, onRestartClicked: () -> Unit) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HELAL LEN YUSUFİ", fontSize = 18.sp)

        CustomButton(
            buttonText = "Baştan başla",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                onRestartClicked.invoke()
            })
    }
}
