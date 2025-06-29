package com.pab.nusabite.data.remote

import com.pab.nusabite.data.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products")
    suspend fun getProductsByCategory(@Query("category") category: String): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductsResponse>
}
