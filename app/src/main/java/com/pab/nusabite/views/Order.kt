package com.pab.nusabite.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pab.nusabite.components.order.DetailScreen
import com.pab.nusabite.components.order.MainScreen
import com.pab.nusabite.utils.models.MenuViewModel

@Composable
fun OrderView(navController: androidx.navigation.NavController) {
    val menuViewModel: MenuViewModel = viewModel()

    // Langsung tampilkan MainScreen (tanpa NavHost tambahan)
    MainScreen(navController = navController, viewModel = menuViewModel)
}