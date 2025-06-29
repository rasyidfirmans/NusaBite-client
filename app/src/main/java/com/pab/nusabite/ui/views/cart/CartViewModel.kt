package com.pab.nusabite.ui.views.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pab.nusabite.data.model.CartRequest
import com.pab.nusabite.data.model.CartResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel: ViewModel() {
    private val cartRepository = CartRepository()

    private val _cartItems = MutableStateFlow<ApiResult<CartResponse>>(ApiResult.Loading)
    val cartItems: StateFlow<ApiResult<CartResponse>> = _cartItems

    private val _addToCartResult = MutableStateFlow<ApiResult<CartResponse>?>(null)
    val addToCartResult: StateFlow<ApiResult<CartResponse>?> = _addToCartResult

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        viewModelScope.launch {
//            _cartItems.value = ApiResult.Loading
            try {
                val result = cartRepository.getItemsInCart()
                _cartItems.value = result
            } catch (e: Exception) {
                _cartItems.value = ApiResult.Error(e)
            }
        }
    }

    fun addToCart(cartRequest: CartRequest) {
        viewModelScope.launch {
            _addToCartResult.value = ApiResult.Loading
            try {
                val result = cartRepository.addToCart(cartRequest)
                _addToCartResult.value = result
                fetchCartItems()
            } catch (e: Exception) {
                _addToCartResult.value = ApiResult.Error(e)
            }
        }
    }

    fun updateQuantity(cartId: Int, cartRequest: CartRequest) {
        viewModelScope.launch {
            try {
                val result = cartRepository.updateQuantity(cartId, cartRequest)
                _addToCartResult.value = result
                fetchCartItems()
            } catch (e: Exception) {
                _addToCartResult.value = ApiResult.Error(e)
            }
        }
    }

    fun deleteItemFromCart(cartId: Int, productId: Int) {
        viewModelScope.launch {
            try {
                val result = cartRepository.deleteItemFromCart(cartId, productId)
                _addToCartResult.value = result
                fetchCartItems()
            } catch (e: Exception) {
                _addToCartResult.value = ApiResult.Error(e)
            }
        }
    }

    fun deleteCart(cartId: Int) {
        viewModelScope.launch {
            try {
                val result = cartRepository.deleteCart(cartId)
                _addToCartResult.value = result
                fetchCartItems()
            } catch (e: Exception) {
                _addToCartResult.value = ApiResult.Error(e)
            }
        }
    }

    fun resetAddToCartResult() {
        _addToCartResult.value = null
    }
}
