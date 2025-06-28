package com.pab.nusabite.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    val cartIdReady = viewModel.getCartId() != null
    var showClearDialog by remember { mutableStateOf(false) }


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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { showClearDialog = true},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                        shape = MaterialTheme.shapes.medium,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = "Clear Cart",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }


                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp)
                ) {
                    items(cartItems) { item ->
                        CartItemCard(
                            item = item,
                            cartId = viewModel.getCartId(),
                            onDelete = {
                                viewModel.removeItemFromCart(item.productId)
                            }
                        )
                    }

                }

                PaymentSummary(cartItems)

                Button(
                    onClick = {
                        viewModel.checkoutCart()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE8C00)), // Hijau
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Checkout", color = Color.White)
                }
            }
        }
    }
    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Apakah kamu yakin ingin menghapus semua item di keranjang?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.clearAllCartItems()
                        showClearDialog = false
                    }
                ) {
                    Text("Ya", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

}


