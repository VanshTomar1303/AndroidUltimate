package com.vansh.bankingappui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.vansh.bankingappui.ui.theme.orange
import com.vansh.bankingappui.ui.theme.pink

data class Finance(
    val icon: ImageVector,
    val name: String,
    val iconBackground: Color
){
    companion object{
        val financeItem: List<Finance> = listOf(
            Finance(
                icon = Icons.Rounded.Star,
                name = "My\nBusiness",
                iconBackground = pink,
            ),
            Finance(
                icon = Icons.Default.Wallet,
                name = "My\n" +
                        "Wallet",
                iconBackground = orange,
            ),
            Finance(
                icon = Icons.Rounded.Analytics,
                name = "Finance\nAnalytics",
                iconBackground = Color.Magenta,
            ),
            Finance(
                icon = Icons.Rounded.MonetizationOn,
                name = "My\nTransaction",
                iconBackground = Color.Cyan,
            )
        )
    }
}
