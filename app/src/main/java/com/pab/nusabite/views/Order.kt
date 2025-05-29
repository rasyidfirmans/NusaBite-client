package com.pab.nusabite.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pab.nusabite.ui.DetailScreen
import com.pab.nusabite.ui.MainScreen
import com.pab.nusabite.utils.models.MenuViewModel

@Composable
fun OrderView() {
    val navController = rememberNavController()
    val menuViewModel: MenuViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(navController, menuViewModel)
        }
        composable(
            "detail/{menuId}",
            arguments = listOf(navArgument("menuId") { type = NavType.IntType })
        ) { backStackEntry ->
            val menuId = backStackEntry.arguments?.getInt("menuId") ?: 0
            DetailScreen(menuId, navController, menuViewModel)
        }
    }
}
