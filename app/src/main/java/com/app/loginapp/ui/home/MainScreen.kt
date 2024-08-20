package com.app.loginapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.loginapp.theme.Pink
import com.app.loginapp.theme.Purple
import com.app.loginapp.ui.login.LoginScreen
import com.app.loginapp.ui.onboarding.OnboardingScreen
import com.app.loginapp.ui.onboarding.PagerModel
import com.app.loginapp.ui.success.LoginSuccessScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainScreenViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { uiState.pagerList.size }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Purple, Pink
                    )
                )
            )
    ) {
        AnimatedVisibility(visible = uiState.screenType == ScreenType.ONBOARDING) {
            OnboardingScreen(
                pagerState = pagerState,
                pagerList = uiState.pagerList.map { PagerModel(it.text) },
                onSkipClicked = {
                    viewModel.updateUIEvents(
                        event = MainScreenUIEvents.ChangeScreenType(
                            screenType = ScreenType.LOGIN
                        )
                    )
                },
            )

        }
        AnimatedVisibility(visible = uiState.screenType == ScreenType.LOGIN) {
            LoginScreen(
                email = uiState.email,
                updateEmail = {
                    viewModel.updateUIEvents(
                        event = MainScreenUIEvents.UpdateEmail(
                            email = it
                        )
                    )
                },
                password = uiState.password,
                updatePassword = {
                    viewModel.updateUIEvents(
                        event = MainScreenUIEvents.UpdatePassword(
                            password = it
                        )
                    )
                },
                onLoginClicked = { viewModel.updateUIEvents(event = MainScreenUIEvents.OnLoginClicked) },
                passwordError = uiState.passwordError,
                mailError = uiState.emailError
            )
        }
        AnimatedVisibility(visible = uiState.screenType == ScreenType.SUCCESS) {
            LoginSuccessScreen(onRestartClicked = {
                viewModel.updateUIEvents(event = MainScreenUIEvents.ChangeScreenType(screenType = ScreenType.ONBOARDING))
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            })
        }
    }
}