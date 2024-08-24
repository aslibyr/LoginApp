package com.app.loginapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.app.loginapp.theme.LoginAppTheme
import com.app.loginapp.ui.main.MainScreen
import com.app.loginapp.utils.hideStatusBarPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBarPadding(resources = resources, window = window)
        setContent {
            LoginAppTheme {
                Scaffold() { padding ->
                    MainScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}