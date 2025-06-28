package com.pab.nusabite.network

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pab.nusabite.utils.dataclass.CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

data class AddToCartBody(
    @SerializedName("cart_id") val cartId: Int,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("quantity") val quantity: Int
)

interface AddToCartService {
    @POST("cart")
    @Headers("Accept: application/json")
    fun addToCart(
        @Body addToCartBody: AddToCartBody
    ): Call<CartResponse>
}

interface GetAllCartItemsService {
    @GET("cart")
    @Headers("Accept: application/json")
    fun getAllCartItems(): Call<CartResponse>
}

val addToCartService = retrofit.create(AddToCartService::class.java)

fun addToCart(addToCartBody: AddToCartBody, onSuccess: (CartResponse) -> Unit, onError: (String) -> Unit) {
    val request = AddToCartBody(addToCartBody.cartId, addToCartBody.productId, addToCartBody.quantity)
    addToCartService.addToCart(request).enqueue(object : Callback<CartResponse> {
        override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
            if (response.isSuccessful) {
                response.body()?.let { onSuccess(it) } ?: onError("Empty body")
                Log.d("API_SUCCESS", response.body().toString())
            } else {
                onError("Error: ${response.code()}")
            }
        }
        override fun onFailure(call: Call<CartResponse>, t: Throwable) {
            onError("Failure: ${t.message}")
        }
    })
}

fun getAllCartItems(onSuccess: (CartResponse) -> Unit, onError: (String) -> Unit) {
    val service = retrofit.create(GetAllCartItemsService::class.java)
    service.getAllCartItems().enqueue(object : Callback<CartResponse> {
        override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
            if (response.isSuccessful) {
                response.body()?.let { onSuccess(it) } ?: onError("Empty body")
                Log.d("API_SUCCESS", response.body().toString())
            } else {
                onError("Error: ${response.code()}")
            }
        }
        override fun onFailure(call: Call<CartResponse>, t: Throwable) {
            onError("Failure: ${t.message}")
        }
    })
}
