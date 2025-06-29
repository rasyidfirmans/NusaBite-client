package com.pab.nusabite.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pab.nusabite.network.TransactionProduct
import com.pab.nusabite.network.createTransaction
import com.pab.nusabite.network.getAllTransactions
import com.pab.nusabite.utils.dataclass.TransactionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionData>>(emptyList())
    val transactions: StateFlow<List<TransactionData>> = _transactions

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val isTransactionSuccess = mutableStateOf(false)

    fun createNewTransaction(products: List<TransactionProduct>) {
        isLoading.value = true
        errorMessage.value = null
        isTransactionSuccess.value = false

        viewModelScope.launch(Dispatchers.IO) {
            createTransaction(
                products = products,
                onSuccess = {
                    isLoading.value = false
                    isTransactionSuccess.value = true
                    getAllTransactionList()
                },
                onError = { error ->
                    isLoading.value = false
                    errorMessage.value = error
                    isTransactionSuccess.value = false
                }
            )
        }
    }

    fun getAllTransactionList() {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            getAllTransactions(
                onSuccess = { data ->
                    isLoading.value = false
                    _transactions.value = data
                },
                onError = { error ->
                    isLoading.value = false
                    errorMessage.value = error
                }
            )
        }
    }

    fun clearState() {
        isTransactionSuccess.value = false
        errorMessage.value = null
    }
}
