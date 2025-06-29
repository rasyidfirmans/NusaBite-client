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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pab.nusabite.R
import com.pab.nusabite.data.model.Product
import com.pab.nusabite.data.model.ProductsResponse
import com.pab.nusabite.ui.components.order.CategoryRow
import com.pab.nusabite.ui.views.home.ProductViewModel
import com.pab.nusabite.utils.BASE_URL

@Composable
fun MainScreen(
    navController: NavController,
    products: ProductsResponse,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        "Makanan",
        "Minuman",
    )

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
                    .clip(MaterialTheme.shapes.medium)
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
                    Text(
                        text = "Provide the best\nfood for you",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Find by Category",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }

        // Category List
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            CategoryRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryClick = onCategorySelected
            )
        }

        // Menu Items Grid
        val productList = products.data ?: emptyList()
        items(productList) { menu: Product ->
            MenuCardStyled(menu = menu) {
                navController.navigate("detail/${menu.id}")
            }
        }
    }
}

@Composable
fun MenuCardStyled(menu: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = "${BASE_URL}${menu.image}",
                contentDescription = menu.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = menu.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
//                Text(text = "⭐ ${menu.rating}", fontSize = 12.sp, color = Color.Gray)
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
