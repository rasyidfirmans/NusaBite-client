package com.pab.nusabite.utils.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pab.nusabite.network.getCart
import com.pab.nusabite.utils.dataclass.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class CartViewModel : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<CartItem>>(emptyList())
    val cartProducts: StateFlow<List<CartItem>> = _cartProducts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchCart()
    }

    fun fetchCart(cartId: Int = 1) {
        _isLoading.value = true

        // Karena getCart pakai callback, kita tidak bisa langsung pakai suspend di sini.
        getCart(
            cartId = cartId,
            onSuccess = { items ->
                _cartProducts.value = items
                _isLoading.value = false
                _errorMessage.value = null
            },
            onError = { error ->
                _errorMessage.value = error
                _isLoading.value = false
            }
        )
    }
}
