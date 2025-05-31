package com.vansh.firebaseauthapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vansh.firebaseauthapp.screens.HomeScreen
import com.vansh.firebaseauthapp.screens.LoginScreen
import com.vansh.firebaseauthapp.screens.SignUpScreen

@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login_screen",
        builder = {
            composable("login_screen") {
                LoginScreen(modifier,navController,authViewModel)
            }
            composable("signup_screen") {
                SignUpScreen(modifier,navController,authViewModel)
            }
            composable("home_screen") {
                HomeScreen(modifier,navController,authViewModel)
            }
        }
    )
}