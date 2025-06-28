package com.pab.nusabite.components.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun CartItemCard(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(12.dp)
    ) {
        AsyncImage(
            model = "http://10.0.2.2:8000/${item.image}",
            contentDescription = item.name,
            modifier = Modifier
                .size(90.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(item.price),
                color = Color(254, 140, 0),
                style = MaterialTheme.typography.bodyMedium
            )

            // ROW untuk tombol -, jumlah, dan +
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                // Tombol -
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(MaterialTheme.shapes.small)
                        .clickable { onDecrement() }
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("-", style = MaterialTheme.typography.bodyLarge)
                }

                // Jumlah item
                Text(
                    text = "${item.quantity}",
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                // Tombol +
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(MaterialTheme.shapes.small)
                        .clickable { onIncrement() }
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
