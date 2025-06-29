package com.pab.nusabite.ui.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pab.nusabite.data.model.TransactionData
import com.pab.nusabite.ui.views.OrderStatus
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CardOrder(order: TransactionData){
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
                    Text(text = "Order ID #${order.invoiceId}", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    Text(text = order.createdAt, style = MaterialTheme.typography.labelMedium)
//                    Text(text = order.time, style = MaterialTheme.typography.labelSmall)
                }
                val status = if (order.status.equals("Completed", ignoreCase = true)) {
                    OrderStatus.complete
                } else {
                    OrderStatus.process
                }
                StatusChip(status = status)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            order.products.forEach { item ->
                Row (
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text=item.name, modifier = Modifier.weight(1f))
                    Text(text="${item.quantity}x", modifier = Modifier.weight(0.5f), textAlign = TextAlign.Center)
                    Text(text="@${item.price}", modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                }

            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.SpaceBetween
            ){
                Text("Total:", fontWeight = FontWeight.Bold)
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(order.total),
                    fontWeight = FontWeight.Bold
                )

            }

        }
    }
}
