package com.pab.nusabite.components.order

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pab.nusabite.network.addToCart
import com.pab.nusabite.utils.models.MenuViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pab.nusabite.network.AddToCartBody


@Composable
fun DetailScreen(menuId: Int, navController: NavController, viewModel: MenuViewModel = viewModel()) {
    val context = LocalContext.current
    val menus by viewModel.menus.collectAsState()
    val menu = menus.find { it.id == menuId }
    var quantity by remember { mutableStateOf(1) }
    var addSuccess by remember { mutableStateOf(false) }

    val addToCartBody: AddToCartBody = AddToCartBody(
        cartId = 1,
        productId = menuId,
        quantity = quantity
    )

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
                        model = "http://10.0.2.2:8000/${it.image}",
                        contentDescription = null,
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
                            .background(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                RoundedCornerShape(50)
                            )
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }

                    Text(
                        text = "About This Menu",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
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
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color(0xFFFFA500),
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = it.description, style = MaterialTheme.typography.bodyMedium)
                }
            }

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
                        addToCart(
                            addToCartBody,
                            onSuccess = {
                                addSuccess = true
                            },
                            onError = { error ->
                                Toast.makeText(context, "Gagal: $error", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Add to Cart")
                }
            }

            // ✅ Trigger navigasi & toast hanya sekali
            if (addSuccess) {
                LaunchedEffect(Unit) {
                    Toast.makeText(context, "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                    navController.navigate("cart")
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