package com.app.loginapp.ui.home

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUIStateModel())
    val uiState: StateFlow<MainScreenUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, MainScreenUIStateModel()
        )

    fun updateUIEvents(event: MainScreenUIEvents) {
        when (event) {
            is MainScreenUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            is MainScreenUIEvents.ChangeScreenType -> {
                if (event.screenType == ScreenType.ONBOARDING) {
                    _uiState.value = _uiState.value.copy(
                        email = "",
                        emailError = null,
                        password = "",
                        passwordError = null
                    )
                }
                _uiState.value = _uiState.value.copy(screenType = event.screenType)
            }

            is MainScreenUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            MainScreenUIEvents.OnLoginClicked -> {
                checkValidationForLogin()
            }
        }
    }

    private fun validateEmail(): ErrorType? {
        if (_uiState.value.email.isEmpty()) {
            return ErrorType.EMPTY_EMAIL
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()) {
            return ErrorType.INCOMPATIBLE_EMAIL
        }
        return null
    }

    private fun validatePassword(): ErrorType? {
        if (_uiState.value.password.isEmpty()) {
            return ErrorType.EMPTY_PASSWORD
        }
        return null
    }

    private fun checkValidationForLogin() {
        val mailValidation = validateEmail()
        val passwordValidation = validatePassword()
        val hasError = listOf(
            mailValidation,
            passwordValidation,
        ).any {
            it != null
        }
        if (hasError) {
            _uiState.value =
                _uiState.value.copy(emailError = mailValidation, passwordError = passwordValidation)
            return
        }
        _uiState.value = _uiState.value.copy(
            emailError = null,
            passwordError = null,
            screenType = ScreenType.SUCCESS
        )
    }
}