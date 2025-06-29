package com.pab.nusabite.utils.dataclass

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    val message: String,
    val data: TransactionData
)

data class TransactionListResponse(
    val message: String,
    val data: List<TransactionData>
)

data class TransactionData(
    val id: Int,
    val status: String,
    @SerializedName("invoice_id")
    val invoiceId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val products: List<ProductItem>
)

data class ProductItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val quantity: Int,
    val category: String
)