package com.pab.nusabite.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pab.nusabite.data.model.ProductsResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val productRepository = ProductRepository()

    private val _products = MutableStateFlow<ApiResult<ProductsResponse>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<ProductsResponse>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = ApiResult.Loading
            try {
                val result = productRepository.getProducts()
                _products.value = result
            } catch (e: Exception) {
                _products.value = ApiResult.Error(e)
            }
        }
    }

    fun fetchProductsByCategory(category: String) {
        viewModelScope.launch {
            _products.value = ApiResult.Loading
            try {
                val result = productRepository.getProductsByCategory(category)
                _products.value = result
            } catch (e: Exception) {
                _products.value = ApiResult.Error(e)
            }
        }
    }
}
