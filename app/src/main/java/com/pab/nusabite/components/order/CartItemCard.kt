package com.pab.nusabite.components.order

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pab.nusabite.network.BASE_URL
import com.pab.nusabite.utils.dataclass.CartItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartItemCard(item: CartItem, cartId: Int?, onDelete: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = "http://10.0.2.2:8000/${item.image}",
            contentDescription = item.name,
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(item.price),
                color = Color(254, 140, 0),
                style = MaterialTheme.typography.bodyMedium
            )
            Text("Qty: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = {
            Log.d("CART_DELETE_BTN", "Hapus: productId=${item.productId}, cartId=$cartId")
            onDelete()
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Hapus item",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}
