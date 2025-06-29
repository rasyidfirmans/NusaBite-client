package com.pab.nusabite.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pab.nusabite.data.model.CartProduct
import com.pab.nusabite.data.remote.ApiResult
import com.pab.nusabite.ui.components.CartItem
import com.pab.nusabite.ui.components.PaymentSummary
import com.pab.nusabite.ui.views.cart.CartViewModel
import com.pab.nusabite.ui.views.history.TransactionViewModel
import com.pab.nusabite.utils.route.Route

@Composable
fun CartView(
    cartViewModel: CartViewModel,
    transactionViewModel: TransactionViewModel,
    navController: NavController
) {
    val cartState by cartViewModel.cartItems.collectAsState()
    val transactionState by transactionViewModel.createTransactionResult.collectAsState()

    LaunchedEffect(transactionState) {
        if (transactionState is ApiResult.Success) {
            navController.navigate(Route.HISTORY) {
                popUpTo(Route.CART) { inclusive = true }
            }
            transactionViewModel.resetCreateTransactionResult()
        }
    }

    when (cartState) {
        is ApiResult.Loading -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .size(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        is ApiResult.Error -> {
            Text(
                text = "Error: ${(cartState as ApiResult.Error).exception.message}",
                modifier = Modifier.fillMaxSize()
            )
        }
        is ApiResult.Success -> {
            val cartItems = (cartState as ApiResult.Success).data.data.products
            CartViewContent(cartItems, cartViewModel, transactionViewModel)
        }
    }
}

@Composable
fun CartViewContent(
    cartItems: List<CartProduct>,
    cartViewModel: CartViewModel,
    transactionViewModel: TransactionViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(cartItems.size) { index ->
                CartItem(
                    item = cartItems[index],
                    cartViewModel = cartViewModel
                )
            }
        }
        PaymentSummary(cartItems, cartViewModel = cartViewModel, transactionViewModel = transactionViewModel)
    }
}
