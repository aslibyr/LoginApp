package com.app.loginapp.ui.ui.onboarding.pagerutils

fun getPagerList(): List<PagerModel> {
    return listOf(
        PagerModel(
            text = "Keşfetmeye Hazır mısın?",
        ),
        PagerModel(
            text = "İhtiyacın olan her şey bir tık uzağında."
        ),
    )
}

data class PagerModel(
    val text: String
)