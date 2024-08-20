package com.app.loginapp.utils

import android.content.res.Resources
import android.os.Build
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager

fun hideStatusBarPadding(
    resources: Resources,
    window: Window
) {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.decorView.setPadding(0, 0, 0, resources.getDimensionPixelSize(resourceId))
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.decorView.windowInsetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
    }
}