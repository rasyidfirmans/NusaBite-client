package com.pab.nusabite.utils.models

import androidx.lifecycle.ViewModel
import com.pab.nusabite.R
import com.pab.nusabite.utils.dataclass.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MenuViewModel : ViewModel() {

    private val _menus = MutableStateFlow(
        listOf(
            MenuItem(
                1,
                "Ordinary Burgers",
                17230,
                R.drawable.nasgor,
                4.9,
                "Classic burger loved by many."
            ),
            MenuItem(
                2,
                "Burger With Meat",
                12230,
                R.drawable.soto,
                4.9,
                "Burger With Meat is a typical food from our restaurant that is in high demand."
            ),
            MenuItem(
                3,
                "Es Teh",
                3000,
                R.drawable.esthe,
                5.0,
                "Es Teh segar"
            ),
            MenuItem(
                4,
                "Soto Ayam",
                8000,
                R.drawable.soto,
                4.9,
                "Soto ayam khas Boyolali"
            )
        )
    )
    val menus: StateFlow<List<MenuItem>> = _menus
}
