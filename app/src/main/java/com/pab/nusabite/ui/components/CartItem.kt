package com.pab.nusabite.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pab.nusabite.data.model.CartProduct
import com.pab.nusabite.ui.views.cart.CartViewModel
import com.pab.nusabite.utils.BASE_URL
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartItem(
    item: CartProduct,
    cartViewModel: CartViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { /* Handle item click */ }
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        AsyncImage(
            model = "${BASE_URL}${item.image}",
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .weight(0.5f)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(item.price.toDouble()),
                fontWeight = FontWeight.Bold,
                color = Color(254, 140, 0),
                modifier = Modifier
                    .weight(1f)
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Counter(
                    item = item,
                    cartViewModel = cartViewModel,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Item",
                    tint = Color.Red,
                    modifier = Modifier
                        .clip(CircleShape)
//                        .background(color = Color(230 / 255f, 230 / 255f, 230 / 255f, 0.7f))
                        .clickable {
                            cartViewModel.deleteItemFromCart(1, item.id)
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}
