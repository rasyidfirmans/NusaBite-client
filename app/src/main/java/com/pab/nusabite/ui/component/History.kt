package com.pab.nusabite.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pab.nusabite.ui.screen.Order
import com.pab.nusabite.ui.screen.OrderItem
import com.pab.nusabite.ui.screen.OrderStatus

@Composable
fun History(orders: List<Order>){
    val ongoing = orders.filter { it.status == OrderStatus.process }
    val completed = orders.filter { it.status == OrderStatus.complete }

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

@Preview(showBackground = true)
@Composable
fun PreviewHistoryContent() {
    val sampleOrders = listOf(
        Order(
            id = 1,
            date = "21 Mei 2025",
            time = "19:30",
            items = listOf(OrderItem("Nasi Goreng", 1, "15.000")),
            total = "15.000",
            status = OrderStatus.process
        ),
        Order(
            id = 2,
            date = "20 Mei 2025",
            time = "13:00",
            items = listOf(OrderItem("Ayam Bakar", 1, "20.000")),
            total = "20.000",
            status = OrderStatus.complete
        )
    )
    History(sampleOrders)
}

