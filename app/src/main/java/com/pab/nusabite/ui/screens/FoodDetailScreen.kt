package com.pab.nusabite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pab.nusabite.ui.components.DescriptionSection
import com.pab.nusabite.ui.components.FoodImage
import com.pab.nusabite.ui.components.PriceSection
import com.pab.nusabite.ui.components.QuantitySelectorWithButton
import com.pab.nusabite.ui.components.TitleSection
import com.pab.nusabite.ui.theme.NusaBiteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(onBackClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "About This Menu",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )

            FoodImage()
            TitleSection()
            PriceSection()

            Spacer(modifier = Modifier.height(24.dp))
            DescriptionSection()
            Spacer(modifier = Modifier.height(24.dp))
        }

        QuantitySelectorWithButton()
    }
}

@Preview(showBackground = true)
@Composable
fun FoodDetailScreenPreview() {
    NusaBiteTheme {
        FoodDetailScreen()
    }
}
