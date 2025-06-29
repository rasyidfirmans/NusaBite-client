package com.pab.nusabite.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pab.nusabite.ui.DetailScreen
import com.pab.nusabite.ui.MainScreen
import com.pab.nusabite.ui.views.home.ProductViewModel

@Composable
fun OrderView(productViewModel: ProductViewModel = viewModel()) {
    var selectedCategory by remember { mutableStateOf("Makanan") }
    LaunchedEffect(selectedCategory) {
        productViewModel.fetchProductsByCategory(selectedCategory)
    }

    val productState by productViewModel.products.collectAsState()
    when (productState) {
        is com.pab.nusabite.data.remote.ApiResult.Loading -> {
            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .size(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = androidx.compose.ui.Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        is com.pab.nusabite.data.remote.ApiResult.Error -> {
            Text(
                text = "Error: ${(productState as com.pab.nusabite.data.remote.ApiResult.Error).exception.message}",
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            )
        }
        is com.pab.nusabite.data.remote.ApiResult.Success -> {
            val products = (productState as com.pab.nusabite.data.remote.ApiResult.Success).data
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    MainScreen(
                        navController = navController,
                        products = products,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it }
                    )
                }
                composable(
                    "detail/{menuId}",
                    arguments = listOf(navArgument("menuId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val menuId = backStackEntry.arguments?.getInt("menuId") ?: 0
                    DetailScreen(menuId, navController, productViewModel)
                }
            }
        }
    }

}
