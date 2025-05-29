package com.pab.nusabite.components.history

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.pab.nusabite.views.OrderStatus
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusChip(status: OrderStatus){
    val Orange = Color(0xFFFFE8C00)
    val ijo = Color(0xFF4CAF50)

    val bgColor = when (status) {
        OrderStatus.process -> Orange
        OrderStatus.complete -> ijo
    }
    val text = when (status) {
        OrderStatus.process -> "In Process"
        OrderStatus.complete -> "Completed"

    }

    Box(modifier = Modifier.background(bgColor, RoundedCornerShape(16.dp))
        .padding(horizontal = 18.dp, vertical = 8.dp)
    ) {

        val LabelStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(text = text, style = LabelStyle)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStatusChip(){
    Column {
        StatusChip(OrderStatus.process)
        Spacer(modifier = Modifier.height(8.dp))
        StatusChip(OrderStatus.complete)
    }
}
