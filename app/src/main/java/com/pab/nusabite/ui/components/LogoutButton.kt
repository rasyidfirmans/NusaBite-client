package com.pab.nusabite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LogoutButton(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.large)
            .background(
                color = if (interactionSource.collectIsPressedAsState().value) Color.Red else Color.Transparent
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { /* Handle click */ }
            .padding(16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = "Logout Icon",
            tint = if (interactionSource.collectIsPressedAsState().value) Color.White else Color.Red,
        )
        Text(
            text = "Logout",
            fontWeight = FontWeight.Bold,
            color = if (interactionSource.collectIsPressedAsState().value) Color.White else Color.Red
        )
    }
}
