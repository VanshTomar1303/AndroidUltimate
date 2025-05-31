package com.vansh.bankingappui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vansh.bankingappui.data.BottomBarItem

@Composable
fun BottomNavBar() {
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            var selectedItem by remember {
                mutableStateOf(0)
            }
            BottomBarItem.item.forEachIndexed {
                i , item ->
                NavigationBarItem(
                    selected = i == selectedItem,
                    onClick = {
                        selectedItem = i
                    },
                    icon = {
                        Icon(
                            imageVector =
                            if(i == selectedItem) item.selectedIcon
                            else item.unSelectedIcon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}