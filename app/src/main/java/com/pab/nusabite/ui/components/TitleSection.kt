package com.pab.nusabite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Burger With Meat 🍔", fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}