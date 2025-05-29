package com.pab.nusabite.components

import android.widget.RemoteViews.RemoteView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentSummary() {
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
                text = "10.000.000",
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
                text = "10.000.000",
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PaymentSummaryPreview() {
    PaymentSummary()
}
