package com.pab.nusabite.data.remote

import com.pab.nusabite.data.model.CartRequest
import com.pab.nusabite.data.model.CartResponse
import com.pab.nusabite.data.model.ProductsResponse
import com.pab.nusabite.data.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products")
    suspend fun getProductsByCategory(@Query("category") category: String): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductsResponse>

    @GET("cart")
    suspend fun getItemsInCart(@Query("id") id: Int): Response<CartResponse>

    @POST("cart")
    suspend fun addToCart(@Body reqBody: CartRequest): Response<CartResponse>

    @PUT("cart/{id}")
    suspend fun updateQuantity(
        @Path("id") cartId: Int,
        @Body reqBody: CartRequest
    ): Response<CartResponse>

    @DELETE("cart/{id}")
    suspend fun deleteItemFromCart(
        @Path("id") cartId: Int,
        @Query("id") productId: Int
    ): Response<CartResponse>

    @DELETE("cart/{id}")
    suspend fun deleteCart(@Path("id") cartId: Int): Response<CartResponse>

    @GET("transactions")
    suspend fun getTransactions(): Response<TransactionResponse>

    @FormUrlEncoded
    @POST("transactions")
    suspend fun createTransaction(
        @FieldMap products: Map<String, @JvmSuppressWildcards Any>
    ): Response<CartResponse>
}
