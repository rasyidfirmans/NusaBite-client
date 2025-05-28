package com.pab.nusabite.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileMenuItem(menuName: String, menuIcon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { /* Handle click */ }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = menuIcon,
            contentDescription = "Profile Icon",
        )
        Text(
            text = menuName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "Arrow Forward",
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ProfileMenuItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ProfileMenuItem(
            menuName = "Personal Data",
            menuIcon = Icons.Default.Person
        )
        ProfileMenuItem(
            menuName = "Settings",
            menuIcon = Icons.Default.Person
        )
    }
}
