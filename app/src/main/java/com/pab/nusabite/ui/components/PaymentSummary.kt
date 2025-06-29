package com.pab.nusabite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pab.nusabite.data.model.CartProduct
import com.pab.nusabite.data.model.TransactionProductRequest
import com.pab.nusabite.ui.views.cart.CartViewModel
import com.pab.nusabite.ui.views.history.TransactionViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaymentSummary(
    cartItems: List<CartProduct> = emptyList(),
    cartViewModel: CartViewModel,
    transactionViewModel: TransactionViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Payment Summary",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text (
                text = "Total Items",
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text (
                text = countTotalPrice(cartItems),
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text (
                text = "Delivery Fee",
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text (
                text = "Free",
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text (
                text = "Discount",
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text (
                text = "0.00",
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text (
                text = "Total Payment",
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text (
                text = countTotalPrice(cartItems),
            )
        }
        Button(
            onClick = {
                val products = cartItems.map {
                    TransactionProductRequest(productId = it.id, quantity = it.quantity)
                }
                cartViewModel.deleteCart(1)
                transactionViewModel.createTransaction(products)
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(254, 140, 0),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)

        ) {
            Text(
                text = "Pay Now",
                fontSize = 16.sp,
            )
        }
    }
}

fun countTotalPrice(cartItems: List<CartProduct>): String {
    val total = cartItems.sumOf { it.price.toDouble() * it.quantity }
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return formatter.format(total)
}
