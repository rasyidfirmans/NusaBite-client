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
                "Ayam Goreng",
                18000,
                R.drawable.ayamgoreng,
                4.7,
                "Ayam goreng renyah dengan bumbu khas dan sambal."
            ),
            MenuItem(
                30,
                "Opor Ayam",
                15000,
                R.drawable.oporayam,
                4.9,
                "Opor ayam khas lebaran dengan kuah santan gurih dan ayam empuk."
            ),
            MenuItem(
                3,
                "Iga Bakar",
                25000,
                R.drawable.igabakar,
                4.8,
                "Iga sapi bakar empuk dengan bumbu spesial dan sambal."
            ),
            MenuItem(
                4,
                "Soto Ayam",
                8000,
                R.drawable.soto,
                4.9,
                "Soto ayam khas Boyolali"
            ),
            MenuItem(
                5,
                "Ayam Bakar",
                20000,
                R.drawable.ayambakar,
                4.8,
                "Ayam bakar dengan bumbu khas dan rasa yang menggugah selera."
            ),
            MenuItem(
                6,
                "Bakso",
                12000,
                R.drawable.bakso,
                4.7,
                "Bakso daging sapi kenyal dengan kuah gurih dan pelengkap."
            ),
            MenuItem(
                7,
                "Dimsum",
                15000,
                R.drawable.dimsum,
                4.6,
                "Dimsum kukus pilihan dengan isian ayam dan udang."
            ),
            MenuItem(
                8,
                "Pempek",
                13000,
                R.drawable.pempek,
                4.5,
                "Pempek Palembang dengan cuko khas yang menggoda."
            ),
            MenuItem(
                9,
                "Gado-Gado",
                10000,
                R.drawable.gadogado,
                4.8,
                "Gado-gado segar dengan bumbu kacang kental dan gurih."
            ),
            MenuItem(
                10,
                "Gudeg",
                14000,
                R.drawable.gudeg,
                4.6,
                "Gudeg khas Jogja dengan rasa manis dan lauk lengkap."
            ),
            MenuItem(
                11,
                "Ikan Bakar",
                22000,
                R.drawable.ikanbakar,
                4.7,
                "Ikan bakar segar dengan bumbu pedas manis."
            ),
            MenuItem(
                12,
                "Lumpia",
                9000,
                R.drawable.lumpia,
                4.4,
                "Lumpia Semarang renyah dengan isian sayur dan ayam."
            ),
            MenuItem(
                13,
                "Mie Ayam",
                11000,
                R.drawable.mieayam,
                4.9,
                "Mie ayam dengan topping ayam manis gurih dan pangsit."
            ),
            MenuItem(
                14,
                "Nasi Goreng",
                12000,
                R.drawable.nasgor2,
                4.9,
                "Nasi goreng spesial dengan telur dan ayam suwir."
            ),
            MenuItem(
                15,
                "Nasi Bakar",
                13000,
                R.drawable.nasibakar,
                4.8,
                "Nasi bakar dengan isian ayam pedas dibungkus daun pisang."
            ),
            MenuItem(
                16,
                "Rendang",
                18000,
                R.drawable.rendang,
                5.0,
                "Rendang daging sapi empuk dengan rempah khas Minang."
            ),
            MenuItem(
                17,
                "Sate Ayam",
                15000,
                R.drawable.sateayam,
                4.8,
                "Sate ayam dengan bumbu kacang gurih dan lontong."
            ),
            MenuItem(
                18,
                "Selat Solo",
                13000,
                R.drawable.selatsolo,
                4.6,
                "Selat Solo dengan kuah segar dan potongan daging sapi."
            ),
            MenuItem(
                19,
                "Serabi",
                8000,
                R.drawable.serabi,
                4.5,
                "Serabi manis tradisional dengan topping modern."
            ),
            MenuItem(
                20,
                "Udang Bakar",
                22000,
                R.drawable.udangbakar,
                4.7,
                "Udang bakar dengan saus manis pedas dan sambal khas."
            ),
            MenuItem(
                24,
                "Es Cendol",
                8000,
                R.drawable.escendol,
                4.6,
                "Es cendol segar dengan gula merah dan santan dingin."
            ),
            MenuItem(
                25,
                "Es Campur",
                9000,
                R.drawable.escampur,
                4.7,
                "Es campur berbagai buah dan agar-agar dengan sirup manis."
            ),
            MenuItem(
                26,
                "Es Melon Selasih",
                8000,
                R.drawable.esmelonselasih,
                4.6,
                "Es melon segar dengan selasih yang menyejukkan tenggorokan."
            ),
            MenuItem(
                27,
                "Es Cincau",
                7000,
                R.drawable.escincau,
                4.5,
                "Minuman tradisional cincau dengan susu dan gula aren."
            ),
            MenuItem(
                28,
                "Es Teh",
                3000,
                R.drawable.esteh,
                4.8,
                "Es teh manis segar, cocok diminum kapan saja."
            ),
            MenuItem(
                29,
                "Es Jeruk",
                4000,
                R.drawable.esjeruk,
                4.7,
                "Es jeruk segar dari jeruk peras asli, menyegarkan hari Anda."
            )
        )
    )
    val menus: StateFlow<List<MenuItem>> = _menus
}
