package com.vansh.bankingappui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CurrencyRupee
import androidx.compose.material.icons.rounded.CurrencyYen
import androidx.compose.material.icons.rounded.CurrencyYuan
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.ui.graphics.vector.ImageVector

data class Currency(
    val name: String,
    val buy: Float,
    val sell: Float,
    val icon: ImageVector
){
    companion object{
       val currency : List<Currency> = listOf(
           Currency(
               name = "USD",
               buy = 23.47f,
               sell = 24.45f,
               icon = Icons.Rounded.AttachMoney
           ),
           Currency(
               name = "EURO",
               buy = 13.47f,
               sell = 14.45f,
               icon = Icons.Rounded.Euro
           ),
           Currency(
               name = "YEN",
               buy = 26.47f,
               sell = 26.45f,
               icon = Icons.Rounded.CurrencyYen
           ),
           Currency(
               name = "INR",
               buy = 33.47f,
               sell = 34.45f,
               icon = Icons.Rounded.CurrencyRupee
           ),
           Currency(
               name = "YUAN",
               buy = 13.47f,
               sell = 12.45f,
               icon = Icons.Rounded.CurrencyYuan
           )
       )
    }
}
