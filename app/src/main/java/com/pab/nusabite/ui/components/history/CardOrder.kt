package com.pab.nusabite.ui.components.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pab.nusabite.ui.views.Order
import com.pab.nusabite.ui.views.OrderItem
import com.pab.nusabite.ui.views.OrderStatus


@Composable
fun CardOrder(order: Order){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = "Order ID #${order.id}", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    Text(text = order.date, style = MaterialTheme.typography.labelMedium)
                    Text(text = order.time, style = MaterialTheme.typography.labelSmall)
                }
                StatusChip(status = order.status)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            order.items.forEach { item ->
                Row (
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text=item.name, modifier = Modifier.weight(1f))
                    Text(text="${item.qty}x", modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                    Text(text="@${item.price}", modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                }

            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.SpaceBetween
            ){
                Text("Total:", fontWeight = FontWeight.Bold)
                Text("Rp ${order.total}", fontWeight = FontWeight.Bold )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevOrderCard(){
    val order= Order(
        id = 1,
        date = "20 Mei 2025",
        time = "19:30",
        items = listOf(
            OrderItem("Nasi Goreng", 1, "15.000"),
            OrderItem("Es Teh", 2, "5.000")
        ),
        total = "25.000",
        status = OrderStatus.complete
    )
    CardOrder(order)
}
