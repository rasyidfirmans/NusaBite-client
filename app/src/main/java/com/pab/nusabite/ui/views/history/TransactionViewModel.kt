package com.pab.nusabite.ui.views.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pab.nusabite.data.model.TransactionProductRequest
import com.pab.nusabite.data.model.TransactionResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {
    private val transactionRepository = TransactionRepository()

    private val _transactionHistoryResult = MutableStateFlow<ApiResult<TransactionResponse>?>(null)
    val transactionHistoryResult: StateFlow<ApiResult<TransactionResponse>?> = _transactionHistoryResult

    private val _createTransactionResult = MutableStateFlow<ApiResult<TransactionResponse>?>(null)
    val createTransactionResult: StateFlow<ApiResult<TransactionResponse>?> = _createTransactionResult

    fun createTransaction(products: List<TransactionProductRequest>) {
        viewModelScope.launch {
            _createTransactionResult.value = ApiResult.Loading
            try {
                val result = transactionRepository.createTransaction(products)
                _createTransactionResult.value = result
                fetchTransactionHistory()
            } catch (e: Exception) {
                _createTransactionResult.value = ApiResult.Error(e)
            }
        }
    }

    fun fetchTransactionHistory() {
        viewModelScope.launch {
            _transactionHistoryResult.value = ApiResult.Loading
            try {
                val result = transactionRepository.getAllTransactions()
                _transactionHistoryResult.value = result
            } catch (e: Exception) {
                _transactionHistoryResult.value = ApiResult.Error(e)
            }
        }
    }

    fun resetCreateTransactionResult() {
        _createTransactionResult.value = null
    }
}
