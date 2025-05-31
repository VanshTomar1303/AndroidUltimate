package com.vansh.noteapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vansh.noteapp.R
import com.vansh.noteapp.model.UserState
import com.vansh.noteapp.navigation.NoteNavigationItem
import com.vansh.noteapp.ui.theme.colorBlack
import com.vansh.noteapp.ui.theme.colorRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    authState: UserState
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
                .background(colorBlack),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Note App With Firebase",
                    color = colorRed,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        delay(2500)
        if (authState.isAuthenticated){
            navHostController.navigate(NoteNavigationItem.HomeScreen.route){
                popUpTo(NoteNavigationItem.SplashScreen.route){
                    inclusive = true
                }
            }
        }else{
            navHostController.navigate(NoteNavigationItem.LoginScreen.route){
                popUpTo(NoteNavigationItem.SplashScreen.route){
                    inclusive = true
                }
            }
        }
    }
}