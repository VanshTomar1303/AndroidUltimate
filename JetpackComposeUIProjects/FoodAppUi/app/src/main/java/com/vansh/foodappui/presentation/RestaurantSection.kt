package com.vansh.foodappui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.DeliveryDining
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.foodappui.model.Restaurant

@Preview(showBackground = true)
@Composable
fun RestaurantSection() {
    LazyColumn{
        items(Restaurant.restaurants) {
            RestaurantItem(it)
        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant){
    Column(
        modifier = Modifier
            .height(250.dp)
            .padding(16.dp)
            .shadow(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        Image(
            modifier = Modifier.height(150.dp)
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(restaurant.image),
            contentDescription = restaurant.name,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = restaurant.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = restaurant.dishes.joinToString(" - "),
            fontSize = 12.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Rating"
                )
                Text(
                    text = ""+restaurant.rating,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = Icons.Rounded.DeliveryDining,
                contentDescription = "Delivery"
            )
            Text(
                text = restaurant.delivery,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = Icons.Outlined.AccessTime,
                contentDescription = "Time"
            )
            Text(
                text ="${restaurant.time} min",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}