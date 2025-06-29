package com.pab.nusabite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pab.nusabite.data.model.CartRequest
import com.pab.nusabite.data.model.ProductsResponse
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.ui.views.cart.CartViewModel
import com.pab.nusabite.ui.views.home.ProductViewModel
import com.pab.nusabite.utils.BASE_URL

@Composable
fun DetailScreen(
    menuId: Int,
    navController: NavController,
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onAddToCartSuccess: () -> Unit
) {
    val productsState by viewModel.products.collectAsState()
    val addToCartResult by cartViewModel.addToCartResult.collectAsState()

    LaunchedEffect(addToCartResult) {
        if (addToCartResult is ApiResult.Success) {
            navController.popBackStack()
            onAddToCartSuccess()
            cartViewModel.resetAddToCartResult()
        }
    }

    when (productsState) {
        is ApiResult.Success -> {
            val products = (productsState as ApiResult.Success<ProductsResponse>).data.data
            val menu = products.find { it.id == menuId }
            var quantity by remember { mutableStateOf(1) }

            menu?.let {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 90.dp)
                    ) {
                        Box(modifier = Modifier.height(300.dp)) {
                            AsyncImage(
                                model = "${BASE_URL}${it.image}",
                                contentDescription = it.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                            )
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.TopStart)
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f), RoundedCornerShape(50))
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                            }

                            Text(
                                text = "About This Menu",
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(16.dp)
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Rp${it.price}",
                                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFFFFA500), fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            HorizontalDivider()

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = it.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    // Tombol bawah
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { if (quantity > 1) quantity-- }) {
                            Icon(Icons.Default.Remove, contentDescription = "Kurangi")
                        }

                        Text(
                            quantity.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        IconButton(onClick = { quantity++ }) {
                            Icon(Icons.Default.Add, contentDescription = "Tambah")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = {
                                val cartRequest = CartRequest(
                                    cartId = 1,
                                    productId = it.id,
                                    quantity = quantity
                                )
                                cartViewModel.addToCart(cartRequest)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Add to Cart")
                        }
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Menu tidak ditemukan", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        is ApiResult.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Terjadi kesalahan: ${(productsState as ApiResult.Error).exception.message}", style = MaterialTheme.typography.bodyLarge)
            }
        }
        is ApiResult.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Memuat data...", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }}
