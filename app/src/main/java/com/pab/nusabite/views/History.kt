package com.pab.nusabite.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pab.nusabite.components.history.TransactionCard
import com.pab.nusabite.network.getAllTransactions
import com.pab.nusabite.utils.dataclass.TransactionData
import com.pab.nusabite.utils.models.CartViewModel
import com.pab.nusabite.viewmodel.TransactionViewModel

@Composable
fun HistoryView(
    viewModel: TransactionViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val transactions by viewModel.transactions.collectAsState()
    val isLoading by remember { viewModel.isLoading }
    val errorMessage by remember { viewModel.errorMessage }

    LaunchedEffect(Unit) {
        viewModel.getAllTransactionList()
    }

    val ongoingOrders = transactions.filter { it.status.lowercase() in listOf("pending", "in_progress") }
    val pastOrders = transactions.filterNot { it.status.lowercase() in listOf("pending", "in_progress") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Order History",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            textAlign = TextAlign.Center
        )

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null -> {
                Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            }

            transactions.isEmpty() -> {
                Text("Belum ada transaksi.")
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (ongoingOrders.isNotEmpty()) {
                        item {
                            Text(
                                text = "Ongoing Orders",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        items(ongoingOrders) { transaction ->
                            TransactionCard(transaction)
                        }
                    }

                    if (pastOrders.isNotEmpty()) {
                        item {
                            Text(
                                text = "Past Orders",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                        }
                        items(pastOrders) { transaction ->
                            TransactionCard(transaction)
                        }
                    }
                }
            }
        }
    }
}
