package com.app.loginapp.ui.home

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.loginapp.R
import com.app.loginapp.theme.Pink
import com.app.loginapp.theme.Purple
import com.app.loginapp.ui.login.LoginScreen
import com.app.loginapp.ui.onboarding.OnboardingScreen
import com.app.loginapp.ui.onboarding.PagerModel
import com.app.loginapp.ui.register.RegisterScreen
import com.app.loginapp.ui.success.LoginSuccessScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val pagerList = listOf(
        PagerModel(
            text = stringResource(R.string.onboarding_first_message),
            image = R.drawable.illustration_girl
        ),
        PagerModel(
            text = stringResource(R.string.onboarding_second_message),
            image = R.drawable.illustration_boy
        ),
    )
    val pagerState = rememberPagerState { pagerList.size }

    BackHandler {
        if (uiState.screenType == ScreenType.LOGIN) {
            viewModel.updateUIEvents(
                event = MainScreenUIEvents.ChangeScreenType(
                    screenType = ScreenType.ONBOARDING
                )
            )
            return@BackHandler
        }
        if (uiState.screenType == ScreenType.SUCCESS) {
            viewModel.updateUIEvents(
                event = MainScreenUIEvents.ChangeScreenType(
                    screenType = ScreenType.ONBOARDING
                )
            )
            scope.launch {
                pagerState.animateScrollToPage(0)
            }
            return@BackHandler
        }
    }

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
                pagerList = pagerList.map { PagerModel(it.text, it.image) },
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

            val passwordError =
                if (uiState.passwordError != null) stringResource(
                    id = uiState.passwordError?.errorText ?: 0
                ) else null

            val emailError =
                if (uiState.emailError != null) stringResource(
                    id = uiState.emailError?.errorText ?: 0
                ) else null

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
                passwordError = passwordError,
                mailError = emailError,
                onCreateAccountClicked = {
                    viewModel.updateUIEvents(
                        event = MainScreenUIEvents.ChangeScreenType(
                            screenType = ScreenType.REGISTER
                        )
                    )
                }
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
        AnimatedVisibility(visible = uiState.screenType == ScreenType.REGISTER) {
            RegisterScreen(
                email =,
                updateEmail =,
                password =,
                updatePassword =,
                onLoginClicked = { /*TODO*/ })
        }
    }
}