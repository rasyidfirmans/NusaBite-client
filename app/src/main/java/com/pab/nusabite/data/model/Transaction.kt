package com.pab.nusabite.data.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    val code: Int,
    val message: String,
    val data: List<TransactionData>
)

data class TransactionData(
    val status: String,
    @SerializedName("invoice_id")
    val invoiceId: String,
    @SerializedName("created_at")
    val createdAt: String,
    val total: Double,
    val products: List<TransactionProduct>
)

data class TransactionProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
    val quantity: Int
)

data class TransactionRequest(
    val products: List<TransactionProductRequest>
)

data class TransactionProductRequest(
    @SerializedName("product_id")
    val productId: Int,
    val quantity: Int
)
