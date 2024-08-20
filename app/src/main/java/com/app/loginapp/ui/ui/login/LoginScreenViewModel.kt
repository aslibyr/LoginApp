package com.app.loginapp.ui.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUIStateModel())
    val uiState: StateFlow<LoginScreenUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, LoginScreenUIStateModel()
        )

    fun updateUIEvents(event: LoginScreenUIEvents) {
        when (event) {
            is LoginScreenUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            is LoginScreenUIEvents.ChangeScreenType -> {
                if (event.screenType == LoginScreenType.ONBOARDING) {
                    _uiState.value = _uiState.value.copy(email = "", password = "")
                }
                _uiState.value = _uiState.value.copy(screenType = event.screenType)
            }

            is LoginScreenUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }
        }
    }
}