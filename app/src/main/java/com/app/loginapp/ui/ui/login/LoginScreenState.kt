package com.app.loginapp.ui.ui.login

import com.app.loginapp.ui.ui.onboarding.pagerutils.PagerModel
import com.app.loginapp.ui.ui.onboarding.pagerutils.getPagerList

sealed class LoginScreenUIEvents {

    data class UpdateEmail(val email: String) : LoginScreenUIEvents()

    data class UpdatePassword(val password: String) : LoginScreenUIEvents()

    data class ChangeScreenType(val screenType: LoginScreenType) : LoginScreenUIEvents()
}

data class LoginScreenUIStateModel(
    val password: String = "",
    val email: String = "",
    val screenType: LoginScreenType = LoginScreenType.ONBOARDING,
    val isLoading: Boolean = false,
    val pagerList: List<PagerModel> = getPagerList()
)

enum class LoginScreenType {
    ONBOARDING, LOGIN, SUCCESS
}