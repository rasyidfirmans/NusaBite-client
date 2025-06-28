package com.pab.nusabite.utils.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pab.nusabite.network.DeleteCartItemBody
import com.pab.nusabite.network.clearCart
import com.pab.nusabite.network.deleteCartItem
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

    private var cartId: Int? = null

    init {
        fetchCartItems()
    }

    fun removeItemFromCart(productId: Int) {
        val currentCartId = cartId

        Log.d("CART_DELETE", "cartId = $currentCartId, productId = $productId")

        if (productId == 0) {
            Log.e("CART_DELETE", "productId yang dikirim bernilai 0! Periksa sumber datanya.")
            _errorMessage.value = "Product ID tidak valid (0)"
            return
        }

        if (currentCartId == null) {
            _errorMessage.value = "Cart ID belum tersedia"
            Log.e("CART_DELETE", "Gagal hapus: cartId null")
            return
        }

        val body = DeleteCartItemBody(
            cartId = currentCartId,
            productId = productId
        )

        deleteCartItem(
            deleteCartItemBody = body,
            onSuccess = {
                _cartProducts.value = _cartProducts.value.filterNot { it.productId == productId }
                Log.d("CART_DELETE", "Berhasil hapus productId=$productId dari cartId=$currentCartId")
            },
            onError = { error ->
                _errorMessage.value = error
                Log.e("CART_DELETE", "Gagal hapus productId=$productId: $error")
            }
        )
    }

    fun getCartId(): Int? = cartId

    private fun fetchCartItems () {
        _isLoading.value = true
        getAllCartItems(
            onSuccess = { response ->
                cartId = response.data.id // <-- simpan cartId dari response
                _cartProducts.value = response.data.products
                _isLoading.value = false
            }
            ,
            onError = { error ->
                _errorMessage.value = error
                _isLoading.value = false
            }
        )
    }

    fun clearAllCartItems() {
        val currentCartId = cartId
        if (currentCartId == null) {
            _errorMessage.value = "Cart ID tidak ditemukan"
            return
        }

        clearCart(
            cartId = currentCartId,
            onSuccess = {
                _cartProducts.value = emptyList() // clear list di UI
            },
            onError = { error ->
                _errorMessage.value = error
            }
        )
    }

    fun checkoutCart() {
        val currentCartId = cartId
        if (currentCartId == null) {
            _errorMessage.value = "Cart ID tidak ditemukan"
            return
        }

        clearCart(
            cartId = currentCartId,
            onSuccess = {
                _cartProducts.value = emptyList()
                Log.d("CHECKOUT", "Checkout berhasil, cart dikosongkan")
            },
            onError = { error ->
                _errorMessage.value = error
                Log.e("CHECKOUT", "Gagal checkout: $error")
            }
        )
    }

}
