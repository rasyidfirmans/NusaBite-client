package com.pab.nusabite.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pab.nusabite.R
import com.pab.nusabite.components.LogoutButton
import com.pab.nusabite.components.ProfileMenuItem
import com.pab.nusabite.utils.dataclass.ProfileMenu

val ProfileMenuList = listOf(
    ProfileMenu(
        name = "Personal Data",
        icon = Icons.Default.Person
    ),
    ProfileMenu(
        name = "Settings",
        icon = Icons.Default.Settings
    ),
    ProfileMenu(
        name = "Extra Card",
        icon = Icons.Default.CreditCard
    )
)

val SupportMenuList = listOf(
    ProfileMenu(
        name = "Help Center",
        icon = Icons.Default.Info
    ),
    ProfileMenu(
        name = "Request Account Deletion",
        icon = Icons.Default.RestoreFromTrash
    ),
    ProfileMenu(
        name = "Add Another Account",
        icon = Icons.Default.PersonAddAlt1
    )
)

@Composable
fun Profile(modifier: Modifier = Modifier) {
    // Placeholder for the Profile screen content
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Profile Settings",
            fontWeight = FontWeight.Bold,
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
        )
        Text(
            text = "Rosiana Pusing",
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "rosipusing@gmail.com",
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Profile Menu Section
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text (
                text = "Profile",
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
            ProfileMenuList.forEach() { menu ->
                ProfileMenuItem(
                    menuName = menu.name,
                    menuIcon = menu.icon
                )
            }
        }

        // Profile Support Section
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text (
                text = "Support",
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp)
            )
            SupportMenuList.forEach() { menu ->
                ProfileMenuItem(
                    menuName = menu.name,
                    menuIcon = menu.icon
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LogoutButton()
    }
}

@Preview (showBackground = true)
@Composable
fun ProfilePreview() {
    Profile()
}
