package com.pab.nusabite.components.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun CartItemCard(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = BASE_URL + item.image,
            contentDescription = item.name,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .height(100.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(item.price),
                color = Color(254, 140, 0),
                style = MaterialTheme.typography.bodyMedium
            )
            Text("Qty: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
