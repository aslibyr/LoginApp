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
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIStateModel())
    val uiState: StateFlow<HomeScreenUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, HomeScreenUIStateModel()
        )

    fun updateUIEvents(event: HomeScreenUIEvents) {
        when (event) {
            is HomeScreenUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            is HomeScreenUIEvents.ChangeScreenType -> {
                if (event.screenType == ScreenType.ONBOARDING) {
                    _uiState.value = _uiState.value.copy(email = "", password = "")
                }
                _uiState.value = _uiState.value.copy(screenType = event.screenType)
            }

            is HomeScreenUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            HomeScreenUIEvents.OnLoginClicked -> {
                checkValidationForLogin()
            }
        }
    }

    private fun validateEmail(): String? {
        if (_uiState.value.email.isEmpty()) {
            return "E-posta boş olamaz."
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()) {
            return "Lütfen geçerli bir e-posta giriniz."
        }
        return null
    }

    private fun validatePassword(): String? {
        if (_uiState.value.password.isEmpty()) {
            return "Şifre boş olamaz."
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
            !it.isNullOrEmpty()

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