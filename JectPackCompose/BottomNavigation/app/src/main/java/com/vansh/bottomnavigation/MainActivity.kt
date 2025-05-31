package com.vansh.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.vansh.bottomnavigation.ui.theme.BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationTheme {

                var selectedIcon by remember {
                    mutableStateOf(0)
                }
                Scaffold(
                  bottomBar = {
                      NavigationBar {
                          BottomNavItem.item.forEachIndexed { index,item ->
                              NavigationBarItem(
                                  selected = index==selectedIcon,
                                  onClick = {
                                      selectedIcon = index
                                  },
                                  icon = {
                                      BadgedBox(
                                          badge = {
                                              if(item.badges!=0){
                                                  Badge{
                                                      Text(
                                                          text = item.badges.toString()
                                                      )
                                                  }
                                              }else if(item.hasNews){
                                                  Badge()
                                              }
                                          }
                                      ){
                                          Icon(
                                              imageVector =
                                                  if(index == selectedIcon)
                                                      item.selectedIcon
                                                  else
                                                      item.unselectedIcon,
                                              contentDescription = item.title
                                          )
                                      }
                                  },
                                  label = {
                                      Text(text = item.title)
                                  }
                              )
                          }
                      }
                  }
                ) {
                    val padding = it
                }
            }
        }
    }
}
