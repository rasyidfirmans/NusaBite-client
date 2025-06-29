package com.pab.nusabite.network

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pab.nusabite.utils.dataclass.CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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

data class DeleteCartItemBody (
    @SerializedName("cart_id") val cartId: Int,
    @SerializedName("product_id") val productId: Int,
)
interface DeleteCartItemService {
    @HTTP(method = "DELETE", path = "cart", hasBody = true)
    @Headers("Accept: application/json",
        "Content-Type: application/json")
    fun deleteCartItem(
        @Body deleteCartItemBody: DeleteCartItemBody
    ): Call<CartResponse>
}

val deleteCartItemService = retrofit.create(DeleteCartItemService::class.java)

fun deleteCartItem(
    deleteCartItemBody: DeleteCartItemBody,
    onSuccess: (CartResponse) -> Unit,
    onError: (String) -> Unit
) {
    deleteCartItemService.deleteCartItem(deleteCartItemBody)
        .enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Empty body")
                    Log.d("DELETE_CART_SUCCESS", response.body().toString())
                } else {
                    onError("Error: ${response.code()}")
                    Log.e("DELETE_CART_ERROR", response.errorBody()?.string() ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                onError("Failure: ${t.message}")
                Log.e("DELETE_CART_FAILURE", t.message ?: "Unknown failure")
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

interface ClearCartService {
    @DELETE("cart/{id}")
    @Headers("Accept: application/json")
    fun clearCart(@Path("id") cartId: Int): Call<CartResponse>
}


val clearCartService = retrofit.create(ClearCartService::class.java)

fun clearCart(
    cartId: Int,
    onSuccess: (CartResponse) -> Unit,
    onError: (String) -> Unit
) {
    val service = retrofit.create(ClearCartService::class.java)
    service.clearCart(cartId).enqueue(object : Callback<CartResponse> {
        override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Empty body")
            } else {
                onError("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<CartResponse>, t: Throwable) {
            onError("Failure: ${t.message}")
        }
    })
}