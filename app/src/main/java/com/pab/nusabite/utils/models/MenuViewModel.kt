package com.pab.nusabite.utils.models

import androidx.annotation.OptIn
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.rememberNavController
import com.pab.nusabite.utils.dataclass.Product
import com.pab.nusabite.network.fetchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuViewModel : ViewModel() {

    private val _menus = MutableStateFlow<List<Product>>(emptyList())
    val menus: StateFlow<List<Product>> = _menus.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        // Secara default ambil kategori id 1 (misal: makanan)
        fetchMenusFromApi(categoryId = 1)
    }

    @OptIn(UnstableApi::class)
    fun fetchMenusFromApi(categoryId: Int) {
        _isLoading.value = true
        _errorMessage.value = null

        fetchData(categoryId,
            onSuccess = { productList ->
                _menus.value = productList
                _isLoading.value = false
                Log.d("API_SUCCESS", productList.toString())
            },
            onError = { errorMsg ->
                _errorMessage.value = errorMsg
                _isLoading.value = false
                Log.e("API_ERROR", errorMsg)
            }
        )
    }

}