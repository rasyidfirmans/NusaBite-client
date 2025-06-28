package com.pab.nusabite.utils.dataclass

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("id") val productId: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val image: String,
    val description: String? = null,
    val category: String? = null
)
