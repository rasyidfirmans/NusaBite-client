package com.pab.nusabite.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pab.nusabite.R
import com.pab.nusabite.ui.components.CartItem
import com.pab.nusabite.ui.components.HeaderView
import com.pab.nusabite.ui.components.PaymentSummary
import com.pab.nusabite.utils.dataclass.CartItem

val CartItems = listOf(
    CartItem(
        name = "Pempek",
        price = 13.000,
        quantity = 2,
        image = R.drawable.pempek
    ),
    CartItem(
        name = "Ikan Bakar",
        price = 22.000,
        quantity = 4,
        image = R.drawable.ikanbakar
    ),CartItem(
        name = "Sate Ayam",
        price = 15.000,
        quantity = 1,
        image = R.drawable.sateayam2
    ),CartItem(
        name = "Es Cendol",
        price = 8.000,
        quantity = 1,
        image = R.drawable.escendol2
    ),CartItem(
        name = "Iga Bakar",
        price = 25.000,
        quantity = 1,
        image = R.drawable.igabakar
    ),CartItem(
        name = "Ayam Goreng",
        price = 18.000,
        quantity = 3,
        image = R.drawable.ayamgoreng
    ),CartItem(
        name = "Es Melon Selasih",
        price = 8.000,
        quantity = 2,
        image = R.drawable.esmelonselasih2
    ),
)

@Composable
fun CartView() {
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        HeaderView(viewName = "My Cart")
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(CartItems.size) { index ->
                CartItem(item = CartItems[index])
            }
        }
        PaymentSummary()
    }
}

@Preview (showBackground = true)
@Composable
fun CartViewPreview() {
    CartView()
}
