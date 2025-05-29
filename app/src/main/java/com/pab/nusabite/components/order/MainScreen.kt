package com.pab.nusabite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pab.nusabite.R
import com.pab.nusabite.components.order.CategoryItemStyled
import com.pab.nusabite.utils.dataclass.MenuItem
import com.pab.nusabite.utils.models.MenuViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MenuViewModel) {
    val menuItems by viewModel.menus.collectAsState()

    // List of categories and their icons
    val categories = listOf(
        "Makanan" to R.drawable.icons_food,
        "Minuman" to R.drawable.icons_lemonade,
        "Dessert" to R.drawable.icons_strawberrycheesecake,
        "Snack" to R.drawable.icons_frenchfries,
        "Daging" to R.drawable.icons_steak,
        "Ikan" to R.drawable.icons_fishfood,
        "Sayur" to R.drawable.icons_broccoli,
        "Ayam" to R.drawable.icons_chicken
    )

    var selectedCategory by remember { mutableStateOf("Makanan") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 0.dp, bottom = 80.dp)
    ) {
        // Header
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .statusBarsPadding()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Text(
                        text = "Provide the best\nfood for you",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Category Title
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Find by Category",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "See All",
                    color = Color(0xFFFFA500),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }

        // Category List
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { (name, iconRes) ->
                    CategoryItemStyled(
                        category = name,
                        iconRes = iconRes,
                        isSelected = selectedCategory == name,
                        onClick = { selectedCategory = name }
                    )
                }
            }
        }

        // Menu Items Grid
        items(menuItems) { menu ->
            MenuCardStyled(menu = menu) {
                navController.navigate("detail/${menu.id}")
            }
        }
    }
}

// Card untuk item menu
@Composable
fun MenuCardStyled(menu: MenuItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = menu.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = menu.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "⭐ ${menu.rating}", fontSize = 12.sp, color = Color.Gray)
                Text(
                    text = "₱ ${menu.price}",
                    color = Color(0xFFFFA500),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
