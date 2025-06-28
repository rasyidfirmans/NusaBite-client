package com.pab.nusabite.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pab.nusabite.components.order.CartItemCard
import com.pab.nusabite.components.HeaderView
import com.pab.nusabite.components.PaymentSummary
import com.pab.nusabite.utils.models.CartViewModel

@Composable
fun CartView(
    viewModel: CartViewModel = viewModel()
) {
    val cartItems by viewModel.cartProducts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Log.d("CartView", "Cart items count: ${cartItems.size}")
    Log.d("CART_ITEMS_DATA", "Cart items: $cartItems")

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderView(viewName = "My Cart")

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null -> {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp)
                ) {
                    items(cartItems) { item ->
                        CartItemCard(
                            item = item,
                            onIncrement = { viewModel.incrementQuantity(item.id, item.quantity) },
                            onDecrement = { viewModel.decrementQuantity(item.id, item.quantity) }
                        )
                    }
                }

                PaymentSummary(cartItems)
            }
        }
    }
}
