package com.pab.nusabite.data.repository

import android.util.Log
import com.pab.nusabite.data.model.CartData
import com.pab.nusabite.data.model.CartRequest
import com.pab.nusabite.data.model.CartResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.remote.RetrofitClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository {
    suspend fun addToCart(cartRequest: CartRequest): ApiResult<CartResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.addToCart(cartRequest)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: CartResponse(0, "", CartData(0, emptyList())))
                } else {
                    ApiResult.Error(Exception("Error adding to cart"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun getItemsInCart(): ApiResult<CartResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getItemsInCart(1)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: CartResponse(0, "", CartData(0, emptyList())))
                } else {
                    Log.e("CartRepository", "Error fetching items in cart: ${response.errorBody()?.string()}")
                    ApiResult.Error(Exception("Error fetching items in cart"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun updateQuantity(cartId: Int, cartRequest: CartRequest): ApiResult<CartResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.updateQuantity(cartId, cartRequest)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: CartResponse(0, "", CartData(0, emptyList())))
                } else {
                    ApiResult.Error(Exception("Error updating quantity"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun deleteItemFromCart(cartId: Int, productId: Int): ApiResult<CartResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteItemFromCart(cartId, productId)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: CartResponse(0, "", CartData(0, emptyList())))
                } else {
                    ApiResult.Error(Exception("Error deleting item from cart"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun deleteCart(cartId: Int): ApiResult<CartResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteCart(cartId)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: CartResponse(0, "", CartData(0, emptyList())))
                } else {
                    ApiResult.Error(Exception("Error deleting cart"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }
}
