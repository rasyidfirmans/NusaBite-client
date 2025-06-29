package com.pab.nusabite.data.repository

import android.util.Log
import com.pab.nusabite.data.model.TransactionData
import com.pab.nusabite.data.model.TransactionProductRequest
import com.pab.nusabite.data.model.TransactionResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.remote.RetrofitClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepository {
    suspend fun createTransaction(products: List<TransactionProductRequest>): ApiResult<TransactionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val fieldMap = mutableMapOf<String, Any>()
                products.forEachIndexed { index, product ->
                    fieldMap["products[$index][product_id]"] = product.productId
                    fieldMap["products[$index][quantity]"] = product.quantity
                }
                val response = apiService.createTransaction(fieldMap)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() as? TransactionResponse ?: TransactionResponse(0, "", emptyList()))
                } else {
                    ApiResult.Error(Exception("Error creating transaction"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun getAllTransactions(): ApiResult<TransactionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTransactions()
                if (response.isSuccessful) {
                    val transactionResponse = response.body()
                    if (transactionResponse != null) {
                        ApiResult.Success(transactionResponse)
                    } else {
                        ApiResult.Error(Exception("Error fetching transactions: Response body is null"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    ApiResult.Error(Exception("Error fetching transactions: ${response.code()}"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }
}
