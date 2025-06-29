package com.pab.nusabite.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Navigation
(
    val title: String,
    val route: String,
    val icon: Array<ImageVector>,
    val isSelected: Boolean = false
) {
    companion object {
        const val PROFILE = "profile"
        const val HOME = "home"
        const val FAVORITES = "favorites"
        const val SETTINGS = "settings"
        const val ABOUT = "about"
    }
}
