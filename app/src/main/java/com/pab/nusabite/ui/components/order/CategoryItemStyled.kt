package com.pab.nusabite.ui.components.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItemStyled(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        Text(
            text = category,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else Color.Transparent
                )
                .clickable { onClick() }
                .padding(8.dp)
                .fillMaxWidth()
        )
}

@Composable
fun CategoryRow(
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            CategoryItemStyled(
                category = category,
                isSelected = selectedCategory == category,
                onClick = { onCategoryClick(category) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemStyledPreview() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            CategoryItemStyled(
                category = "Appetizers",
                isSelected = true,
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
        item {
            CategoryItemStyled(
                category = "Main Course",
                isSelected = false,
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
    }
}
