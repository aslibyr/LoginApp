package com.app.loginapp.ui.home

import android.content.Context
import com.app.loginapp.R

sealed class MainScreenUIEvents {

    data class UpdateEmail(val email: String) : MainScreenUIEvents()

    data class UpdatePassword(val password: String) : MainScreenUIEvents()

    data class ChangeScreenType(val screenType: ScreenType) : MainScreenUIEvents()

    data object OnLoginClicked : MainScreenUIEvents()
}

data class MainScreenUIStateModel(
    val password: String = "",
    val email: String = "",
    val screenType: ScreenType = ScreenType.ONBOARDING,
    val isLoading: Boolean = false,
    val passwordError: ErrorType? = null,
    val emailError: ErrorType? = null
)

enum class ScreenType {
    ONBOARDING, LOGIN, SUCCESS
}

enum class ErrorType(private val errorText: Int) {
    EMPTY_PASSWORD(errorText = R.string.empty_password),
    INCOMPATIBLE_EMAIL(errorText = R.string.incompatible_email),
    EMPTY_EMAIL(errorText = R.string.empty_email);

    fun getErrorMessage(context: Context): String {
        return context.getString(errorText)
    }
}