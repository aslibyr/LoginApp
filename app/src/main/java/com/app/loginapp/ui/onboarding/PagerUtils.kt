package com.app.loginapp.ui.onboarding

import com.app.loginapp.R

fun getPagerList(): List<PagerModel> {
    return listOf(
        PagerModel(
            text = "Keşfetmeye Hazır mısın?",
            image = R.drawable.illustration_girl
        ),
        PagerModel(
            text = "İhtiyacın olan her şey bir tık uzağında.",
            image = R.drawable.illustration_boy
        ),
    )
}

data class PagerModel(
    val text: String,
    val image: Int
)