package com.pab.nusabite.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.ui.components.history.History
import com.pab.nusabite.ui.views.history.TransactionViewModel

// Dummy colors
val Orange = Color(0xFFFFE8C00)
val ijo = Color(0xFF4CAF50)

enum class OrderStatus { process, complete }

// Entry point
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryView(transactionViewModel: TransactionViewModel = viewModel()) {
    val transactionState by transactionViewModel.transactionHistoryResult.collectAsState()

    LaunchedEffect(Unit) {
        transactionViewModel.fetchTransactionHistory()
    }

    when (transactionState) {
        is ApiResult.Loading -> {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        is ApiResult.Error -> {
            Text(
                text = "Error: ${(transactionState as ApiResult.Error).exception.message}",
                modifier = Modifier.fillMaxSize()
            )
        }
        is ApiResult.Success -> {
            val orders = (transactionState as ApiResult.Success).data
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                History(orders.data)
            }
        }
        else -> {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
