package com.app.loginapp.ui.main

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.loginapp.data.UserEntity
import com.app.loginapp.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

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
                if (event.screenType == ScreenType.ONBOARDING || event.screenType == ScreenType.REGISTER) {
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
                checkValidationForAction(isLogin = true)
            }

            is MainScreenUIEvents.OnRegisterClicked -> {
                checkValidationForAction(isLogin = false)
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

    private fun checkValidationForAction(isLogin: Boolean) {
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
        if (isLogin) {
            loginUser()
        } else {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = _uiState.value.email
        val password = _uiState.value.password
        viewModelScope.launch {
            val existingUser = userRepository.getUserByEmail(email)
            if (existingUser != null) {
                _uiState.value = _uiState.value.copy(
                    emailError = ErrorType.EMAIL_ALREADY_EXISTS,
                    passwordError = null
                )
                return@launch
            }

            val user = UserEntity(email = email, password = password)
            userRepository.insertUser(user)

            _uiState.value = _uiState.value.copy(
                screenType = ScreenType.SUCCESS
            )
        }
    }

    private fun loginUser() {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailAndPassword(
                _uiState.value.email,
                _uiState.value.password
            )
            if (user != null) {
                _uiState.value = _uiState.value.copy(
                    emailError = null,
                    passwordError = null,
                    screenType = ScreenType.SUCCESS
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    emailError = ErrorType.INCOMPATIBLE_EMAIL,
                    passwordError = ErrorType.EMPTY_PASSWORD
                )
            }
        }
    }
}