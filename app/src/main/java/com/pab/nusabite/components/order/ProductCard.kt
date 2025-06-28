package com.pab.nusabite.components.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pab.nusabite.utils.dataclass.Product


@Composable
fun ProductCard(menu: Product, onClick: () -> Unit) {
    val imageUrl = "http://10.0.2.2:8000/${menu.image}"

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = menu.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(menu.name, fontWeight = FontWeight.Bold)
                Text("₱ ${menu.price}", color = Color(0xFFFFA500), fontWeight = FontWeight.Bold)
            }
        }
    }
}
