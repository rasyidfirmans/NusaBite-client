package com.pab.nusabite.utils.dataclass

import com.google.gson.annotations.SerializedName

data class CartResponse(
    val code: Int,
    val message: String,
    val data: CartData
)

data class CartData(
    val id: Int,
    val products: List<CartItem>
)

