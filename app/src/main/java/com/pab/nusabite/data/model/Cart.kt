package com.pab.nusabite.data.model

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("cart_id")
    val cartId: Int,
    @SerializedName("product_id")
    val productId: Int,
    val quantity: Int,
)

data class CartResponse(
    val code: Int,
    val message: String,
    val data: CartData,
)

data class CartData(
    @SerializedName("cart_id")
    val cartId: Int,
    val products: List<CartProduct>
)
data class CartProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
    val quantity: Int
)
