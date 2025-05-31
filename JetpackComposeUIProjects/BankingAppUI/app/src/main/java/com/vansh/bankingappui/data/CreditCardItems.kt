package com.vansh.bankingappui.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.vansh.bankingappui.R
import com.vansh.bankingappui.ui.theme.Pink40
import com.vansh.bankingappui.ui.theme.Pink80
import com.vansh.bankingappui.ui.theme.Purple40
import com.vansh.bankingappui.ui.theme.Purple80


data class CreditCardItems(
    @DrawableRes val image: Int,
    val type: String,
    val balance: String,
    val acNo: String,
    val color: Brush
) {
    companion object{
        val item: List<CreditCardItems> = listOf(
            CreditCardItems(
                image = R.drawable.ic_visa,
                type = "Credit",
                balance = "₹10,000",
                acNo = "1234 5678 9012 3456",
                color = getGradient(Purple40, Purple80)
            ),
            CreditCardItems(
                image = R.drawable.ic_mastercard,
                type = "Debit",
                balance = "₹60,000",
                acNo = "1293 4485 3894 1029",
                color = getGradient(Pink40, Pink80)
            )
        )
    }
}
private fun getGradient(
    startColor: Color,
    endColor: Color
): Brush{
    return Brush.horizontalGradient(
        colors = listOf(startColor,endColor)
    )
}

