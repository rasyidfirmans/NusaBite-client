package com.pab.nusabite.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pab.nusabite.R
import com.pab.nusabite.components.CartItem
import com.pab.nusabite.components.HeaderView
import com.pab.nusabite.components.PaymentSummary
import com.pab.nusabite.utils.dataclass.CartItem

val CartItems = listOf(
    CartItem(
        name = "Burger King",
        price = 15.0,
        quantity = 2,
        image = R.drawable.burger
    ),
    CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
    ),CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
    ),CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
    ),CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
    ),CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
    ),CartItem(
        name = "Pizza Hut",
        price = 20.0,
        quantity = 1,
        image = R.drawable.burger
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
