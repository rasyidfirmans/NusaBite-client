package com.pab.nusabite.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pab.nusabite.components.order.CartItemCard
import com.pab.nusabite.components.HeaderView
import com.pab.nusabite.components.PaymentSummary
import com.pab.nusabite.utils.models.CartViewModel

@Composable
fun CartView(
    navController: NavHostController,
    viewModel: CartViewModel = viewModel()
) {
    val cartItems by viewModel.cartProducts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val cartIdReady = viewModel.getCartId() != null
    var showClearDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<Int?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val isCheckoutSuccess by viewModel.isCheckoutSuccess.collectAsState()



    Log.d("CartView", "Cart items count: ${cartItems.size}")
    Log.d("CART_ITEMS_DATA", "Cart items: $cartItems")

    LaunchedEffect(isCheckoutSuccess) {
        if (isCheckoutSuccess) {
            viewModel.clearCheckoutState()
            navController.navigate("history") {
                popUpTo("cart") { inclusive = true }
            }
        }
    }

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
                                itemToDelete = item.productId
                                showDeleteDialog = true
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
                        Toast.makeText(context, "Keranjang berhasil dikosongkan", Toast.LENGTH_SHORT).show()
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
    if (showDeleteDialog && itemToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                itemToDelete = null
            },
            title = { Text("Konfirmasi Hapus Item") },
            text = { Text("Apakah kamu yakin ingin menghapus item ini dari keranjang?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.removeItemFromCart(itemToDelete!!)
                        Toast.makeText(context, "Item berhasil dihapus", Toast.LENGTH_SHORT).show()
                        showDeleteDialog = false
                        itemToDelete = null
                    }
                ) {
                    Text("Ya", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        itemToDelete = null
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }

}