package com.pab.nusabite.network

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pab.nusabite.utils.dataclass.ApiProperty
import com.pab.nusabite.utils.dataclass.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL = "http://10.0.2.2:8000/api/"

interface ApiPropertyApiService {
    @GET("products")
    @Headers("Accept: application/json")
    fun getProductsByCategory(
        @Query("category_id") categoryId: Int
    ): Call<ApiProperty>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val productApiService: ApiPropertyApiService = retrofit.create(ApiPropertyApiService::class.java)

fun fetchData(
    categoryId: Int,
    onSuccess: (List<Product>) -> Unit,
    onError: (String) -> Unit
) {
    productApiService.getProductsByCategory(categoryId).enqueue(object : retrofit2.Callback<ApiProperty> {
        override fun onResponse(call: Call<ApiProperty>, response: Response<ApiProperty>) {
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    Log.d("API_SUCCESS", data.toString())
                    onSuccess(data.data)
                } else {
                    onError("Response body is null")
                }
            } else {
                onError("Error: ${response.code()} ${response.message()}")
            }
        }

        override fun onFailure(call: Call<ApiProperty>, t: Throwable) {
            onError("Failure: ${t.message}")
        }
    })
}