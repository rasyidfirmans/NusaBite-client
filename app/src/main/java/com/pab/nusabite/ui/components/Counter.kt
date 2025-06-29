package com.pab.nusabite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val count = remember { mutableStateOf(1) }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Remove,
            contentDescription = "Decrease",
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color(230 / 255f, 230 / 255f, 230 / 255f, 0.7f))
                .clickable {
                    if (count.value > 1) {
                        count.value -= 1
                    }
                }
                .padding(4.dp)
        )
        Text (
            text = count.value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Increase",
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color(230 / 255f, 230 / 255f, 230 / 255f, 0.7f))
                .clickable {
                    count.value += 1
                }
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    Counter()
}
