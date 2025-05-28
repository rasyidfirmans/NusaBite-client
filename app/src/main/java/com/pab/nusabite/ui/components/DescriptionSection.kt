package com.pab.nusabite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Description", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Burger With Meat is a typical food from our restaurant that is much in demand by many people, this is very recommended for you.",
            fontSize = 14.sp
        )
    }
}