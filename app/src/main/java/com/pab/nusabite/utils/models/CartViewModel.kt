package com.pab.nusabite.utils.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pab.nusabite.network.getAllCartItems
import com.pab.nusabite.network.updateCartItemQuantity
import com.pab.nusabite.utils.dataclass.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<CartItem>>(emptyList())
    val cartProducts: StateFlow<List<CartItem>> = _cartProducts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private var cartId: Int = -1

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        _isLoading.value = true
        getAllCartItems(
            onSuccess = { response ->
                cartId = response.data.id // ✅ ini penting
                _cartProducts.value = response.data.products
                _cartProducts.value = response.data.products
                _isLoading.value = false
                Log.d("CartViewModel", "Fetched cart items: ${response.data.products.size} items")
            },
            onError = { error ->
                _errorMessage.value = error
                _isLoading.value = false
            }
        )
    }

    fun incrementQuantity(productId: Int, currentQuantity: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            updateCartItemQuantity(
                cartId = cartId,
                productId = productId,
                newQuantity = currentQuantity + 1,
                onSuccess = {
                    fetchCartItems()
                },
                onError = { error ->
                    Log.e("CartViewModel", "Failed to increment: $error")
                }
            )
        }
    }

    fun decrementQuantity(productId: Int, currentQuantity: Int) {
        if (currentQuantity > 1) {
            CoroutineScope(Dispatchers.IO).launch {
                updateCartItemQuantity(
                    cartId = cartId,
                    productId = productId,
                    newQuantity = currentQuantity - 1,
                    onSuccess = {
                        fetchCartItems()
                    },
                    onError = { error ->
                        Log.e("CartViewModel", "Failed to decrement: $error")
                    }
                )
            }
        }
    }

}
