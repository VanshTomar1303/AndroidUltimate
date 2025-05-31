package com.vansh.bankingappui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }
    ) {padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding)
        ) {
            TopBar()
            CreditCard()
            Spacer(modifier = Modifier.height(16.dp))
            FinanceSection()
            CurrencySection()
        }
    }
}
