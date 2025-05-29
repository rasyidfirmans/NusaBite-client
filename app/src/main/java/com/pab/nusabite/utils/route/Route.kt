package com.pab.nusabite.utils.route

object Route {
    const val ORDER = "order"
    const val CART = "cart"
    const val HISTORY = "history"
    const val PROFILE = "profile"
    const val ABOUT = "about"
    const val SETTINGS = "settings"

    fun getRoute(route: String): String {
        return when (route) {
            ORDER -> ORDER
            CART -> CART
            HISTORY -> HISTORY
            PROFILE -> PROFILE
            ABOUT -> ABOUT
            SETTINGS -> SETTINGS
            else -> ORDER // Default route
        }
    }
}
