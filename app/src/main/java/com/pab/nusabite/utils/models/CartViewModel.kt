package com.pab.nusabite.utils.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pab.nusabite.network.getAllCartItems
import com.pab.nusabite.utils.dataclass.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<CartItem>>(emptyList())
    val cartProducts: StateFlow<List<CartItem>> = _cartProducts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchCartItems()
    }

    private fun fetchCartItems () {
        _isLoading.value = true
        getAllCartItems(
            onSuccess = { response ->
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
}
