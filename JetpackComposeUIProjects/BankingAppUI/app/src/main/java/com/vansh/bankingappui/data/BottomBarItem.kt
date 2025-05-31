package com.vansh.bankingappui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
){
    companion object{
        val item: List<BottomBarItem> = listOf(
            BottomBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home
            ),
            BottomBarItem(
                title = "Wallet",
                selectedIcon = Icons.Filled.Wallet,
                unSelectedIcon = Icons.Outlined.Wallet
            ),
            BottomBarItem(
                title = "Notification",
                selectedIcon = Icons.Filled.Notifications,
                unSelectedIcon = Icons.Outlined.Notifications
            ),
            BottomBarItem(
                title = "Account",
                selectedIcon = Icons.Filled.AccountCircle,
                unSelectedIcon = Icons.Outlined.AccountCircle
            ),
        )
    }
}
