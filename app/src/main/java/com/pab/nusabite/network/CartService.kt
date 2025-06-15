package com.pab.nusabite.network

import com.pab.nusabite.utils.dataclass.CartItem
import com.pab.nusabite.utils.dataclass.CartResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

// Add to cart (sudah ada)
fun addToCart(
    cartId: Int,
    productId: Int,
    quantity: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    val jsonBody = JSONObject().apply {
        put("cart_id", cartId)
        put("product_id", productId)
        put("quantity", quantity)
    }

    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = jsonBody.toString().toRequestBody(mediaType)

    val request = Request.Builder()
        .url("http://10.0.2.2:8000/api/cart")
        .addHeader("Accept", "application/json")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            onError("Network error: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                onSuccess()
            } else {
                onError("Error: ${response.message}")
            }
        }
    })
}

// Get Cart items (tambahan)
fun getCart(
    cartId: Int,
    onSuccess: (List<CartItem>) -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    val jsonBody = JSONObject().apply {
        put("cart_id", cartId)
    }

    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = jsonBody.toString().toRequestBody(mediaType)

    val request = Request.Builder()
        .url("http://10.0.2.2:8000/api/cart/show")
        .addHeader("Accept", "application/json")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            onError("Network error: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                onError("Server error: ${response.message}")
                return
            }

            try {
                val body = response.body?.string()
                println("RESPONSE BODY: $body")
                if (body.isNullOrBlank()) {
                    onError("Empty response from server")
                    return
                }

                val json = JSONObject(body)

                // ✅ check if backend returns status code & message
                if (json.getInt("code") != 200) {
                    onError("Server returned error code: ${json.getInt("code")}")
                    return
                }

                val data = json.getJSONObject("data")
                val productsArray = data.getJSONArray("products")

                val cartItems = mutableListOf<CartItem>()
                for (i in 0 until productsArray.length()) {
                    val product = productsArray.getJSONObject(i)

                    cartItems.add(
                        CartItem(
                            id = product.getInt("id"),
                            name = product.getString("name"),
                            price = product.getDouble("price"),
                            quantity = product.getInt("quantity"),
                            image = product.getString("image")
                        )
                    )
                }

                onSuccess(cartItems)
            } catch (e: Exception) {
                onError("Parsing error: ${e.message}")
            }
        }
    })
}

