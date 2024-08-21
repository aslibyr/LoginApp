package com.app.loginapp.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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


@Composable
fun BoxScope.SkipButton(
    onSkipClicked: () -> Unit,
    alignment: Alignment,
) {
    Card(
        shape = RoundedCornerShape(
            5.dp
        ),
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .padding(end = 8.dp, bottom = 8.dp)
            .align(alignment)
            .fillMaxSize()
            .clickable {
                onSkipClicked.invoke()
            },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp),
                tint = Color.White
            )
        }
    }
}