package com.pab.nusabite

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pab.nusabite.ui.theme.NusaBiteTheme
import com.pab.nusabite.utils.dataclass.Navigation
import com.pab.nusabite.views.CartView
import com.pab.nusabite.views.OrderView
import com.pab.nusabite.views.Profile

val navigationItems = listOf(
    Navigation(title = "Order", route = "order", icon = arrayOf(Icons.Outlined.Shop, Icons.Filled.Shop)),
    Navigation(title = "Cart", route = "cart", icon = arrayOf(Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart)),
    Navigation(title = "History", route = "history", icon = arrayOf(Icons.Outlined.History, Icons.Filled.History)),
    Navigation(title = "Profile", route = "profile", icon = arrayOf(Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle)),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NusaBiteTheme {
                var selectedNavItemIndex by remember {
                    mutableStateOf(0)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar (
                            modifier = Modifier
                                //TODO: Fix background color
                                .background(Color(255, 255,255))
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )
                        ) {
                            navigationItems.forEachIndexed { index, navItem ->
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color(254, 140, 0),
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                        selectedTextColor = Color(254, 140, 0),
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                        indicatorColor = Color(254, 140, 0).copy( alpha = 0.2f)
                                    ),
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedNavItemIndex) navItem.icon[1] else navItem.icon[0],
                                            contentDescription = navItem.title,
//                                            modifier = Modifier.padding(8.dp)
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = navItem.title,
                                            fontWeight = if (index == selectedNavItemIndex) FontWeight.Bold else FontWeight.Normal,
                                        )
},
                                    selected = selectedNavItemIndex == index,
                                    onClick = {
                                        selectedNavItemIndex = index
                                    },
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OrderView()
    }
}
