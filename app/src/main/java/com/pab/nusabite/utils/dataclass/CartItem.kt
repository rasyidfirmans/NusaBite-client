package com.pab.nusabite.utils.dataclass

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int,
    val image: Int
)
