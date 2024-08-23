package com.app.loginapp.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.loginapp.theme.Black

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    buttonText: String,
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Black),
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        enabled = isEnable
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = buttonText,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            ),
            textAlign = TextAlign.Center
        )
    }
}
