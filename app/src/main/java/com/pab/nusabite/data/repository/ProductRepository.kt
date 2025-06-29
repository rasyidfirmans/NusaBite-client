package com.pab.nusabite.data.repository

import android.util.Log
import com.pab.nusabite.data.model.ProductsResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.remote.RetrofitClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    suspend fun getProducts(): ApiResult<ProductsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()
                if (response.isSuccessful) {
                    Log.d("ProductRepository", "Response: ${response.body()}")
                    ApiResult.Success(response.body() ?: ProductsResponse(0, "", emptyList()))
                } else {
                    ApiResult.Error(Exception("Error fetching products"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun getProductsByCategory(category: String): ApiResult<ProductsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductsByCategory(category)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: ProductsResponse(0, "", emptyList()))
                } else {
                    ApiResult.Error(Exception("Error fetching products by category"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun getProductById(id: Int): ApiResult<ProductsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductById(id)
                if (response.isSuccessful) {
                    ApiResult.Success(response.body() ?: ProductsResponse(0, "", emptyList()))
                } else {
                    ApiResult.Error(Exception("Error fetching product by ID"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }
}
