package com.pab.nusabite.network

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pab.nusabite.utils.dataclass.TransactionData
import com.pab.nusabite.utils.dataclass.TransactionListResponse
import com.pab.nusabite.utils.dataclass.TransactionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

// -----------------------
// REQUEST BODY
// -----------------------

data class TransactionProduct(
    @SerializedName("product_id") val productId: Int,
    val quantity: Int
)

data class CreateTransactionRequest(
    val products: List<TransactionProduct>
)

// -----------------------
// SERVICE INTERFACE
// -----------------------

interface TransactionApiService {
    @POST("transactions")
    @Headers("Accept: application/json")
    fun createTransaction(
        @Body request: CreateTransactionRequest
    ): Call<TransactionResponse>

    @GET("transactions")
    @Headers("Accept: application/json")
    fun getAllTransactions(): Call<TransactionListResponse>
}

// -----------------------
// SERVICE INSTANCES
// -----------------------

val transactionApi = retrofit.create(TransactionApiService::class.java)

// -----------------------
// FUNCTIONS TO CALL API
// -----------------------

fun createTransaction(
    products: List<TransactionProduct>,
    onSuccess: (TransactionResponse) -> Unit,
    onError: (String) -> Unit
) {
    val request = CreateTransactionRequest(products)

    Log.d("API_REQUEST", "Creating transaction with products: $products")

    transactionApi.createTransaction(request).enqueue(object : Callback<TransactionResponse> {
        override fun onResponse(call: Call<TransactionResponse>, response: Response<TransactionResponse>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("API_SUCCESS", "Transaction created: $it")
                    onSuccess(it)
                } ?: onError("Empty response")
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("API_ERROR", "Code: ${response.code()}, Body: $errorBody")
                onError("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
            Log.e("API_FAILURE", "Failed to create transaction: ${t.message}")
            onError("Failure: ${t.message}")
        }
    })
}

fun getAllTransactions(
    onSuccess: (List<TransactionData>) -> Unit,
    onError: (String) -> Unit
) {
    transactionApi.getAllTransactions().enqueue(object : Callback<TransactionListResponse> {
        override fun onResponse(
            call: Call<TransactionListResponse>,
            response: Response<TransactionListResponse>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("API_SUCCESS", "Fetched transactions: ${it.data}")
                    onSuccess(it.data)
                } ?: onError("Empty transaction list")
            } else {
                Log.e("API_ERROR", "Code: ${response.code()}")
                onError("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<TransactionListResponse>, t: Throwable) {
            Log.e("API_FAILURE", "Failed to get transactions: ${t.message}")
            onError("Failure: ${t.message}")
        }
    })
}
