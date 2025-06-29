package com.pab.nusabite.ui.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pab.nusabite.data.model.TransactionData
import com.pab.nusabite.data.model.TransactionResponse
import com.pab.nusabite.ui.views.OrderStatus

@Composable
fun History(orders: List<TransactionData>){
    val ongoing = orders.filter { !it.status.equals("Completed", ignoreCase = true) }
    val completed = orders.filter { it.status.equals("Completed", ignoreCase = true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (ongoing.isNotEmpty()) {
            item { SectionCard(title = "Ongoing Orders") }
            items(ongoing) { CardOrder(order = it) }
        }

        if (completed.isNotEmpty()) {
            item { SectionCard(title = "Past Orders") }
            items(completed) { CardOrder(order = it) }
        }
    }
}
