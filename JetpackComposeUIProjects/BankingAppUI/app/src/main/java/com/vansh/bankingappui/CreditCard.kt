package com.vansh.bankingappui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.bankingappui.data.CreditCardItems

@Composable
fun CreditCard() {
    LazyRow {
        items(CreditCardItems.item){ item ->
            RowItem(item)
        }
    }
}

@Composable
fun RowItem(
    items: CreditCardItems
) {
    Box(
        modifier = Modifier.padding(16.dp)
    ){
        Column(
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
                .background(items.color)
                .width(250.dp)
                .height(160.dp)
                .clickable{

                }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(items.image),
                contentDescription = items.type,
                modifier = Modifier
                    .width(60.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = items.type,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = items.balance,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = items.acNo,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}