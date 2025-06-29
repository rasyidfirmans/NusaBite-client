package com.pab.nusabite.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import com.pab.nusabite.ui.components.HeaderView
import com.pab.nusabite.ui.components.history.History

// Dummy colors
val Orange = Color(0xFFFFE8C00)
val ijo = Color(0xFF4CAF50)

// Data model
data class Order(
    val id: Int,
    val date: String,
    val time: String,
    val items: List<OrderItem>,
    val total: String,
    val status: OrderStatus
)

data class OrderItem(
    val name: String,
    val qty: Int,
    val price: String
)

enum class OrderStatus { process, complete }

val orders = listOf(
    Order(1, "20 Mei 2025", "19:30",
        listOf(
            OrderItem("Nasi Goreng", 1, "15.000"),
            OrderItem("Es Teh", 2, "5.000")
        ),
        "25.000",
        OrderStatus.complete
    ),
    Order(2, "21 Mei 2025", "21:30",
        listOf(
            OrderItem("Nasi Goreng", 1, "15.000"),
            OrderItem("Es Teh", 2, "5.000")
        ),
        "25.000",
        OrderStatus.process
    ),
    Order(3, "21 Mei 2025", "22:30",
        listOf(
            OrderItem("Nasi Goreng", 1, "15.000")
        ),
        "15.000",
        OrderStatus.process
    ),
    Order(4, "21 Mei 2025", "22:30",
        listOf(
            OrderItem("Nasi Goreng", 1, "15.000")
        ),
        "15.000",
        OrderStatus.process
    )
)

// Entry point
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryView( ) {
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        HeaderView(viewName = "Order History")
        History(orders)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    HistoryView()
}
