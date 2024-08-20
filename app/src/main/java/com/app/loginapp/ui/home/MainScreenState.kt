package com.app.loginapp.ui.home

import com.app.loginapp.ui.onboarding.PagerModel
import com.app.loginapp.ui.onboarding.getPagerList

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
    val pagerList: List<PagerModel> = getPagerList(),
    val passwordError: String? = null,
    val emailError: String? = null
)

enum class ScreenType {
    ONBOARDING, LOGIN, SUCCESS
}