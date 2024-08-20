package com.app.loginapp.ui.onboarding

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