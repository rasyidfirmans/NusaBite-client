package com.pab.nusabite.utils.dataclass

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Int,
    val imageResId: Int,
    val rating: Double,
    val description: String
)
