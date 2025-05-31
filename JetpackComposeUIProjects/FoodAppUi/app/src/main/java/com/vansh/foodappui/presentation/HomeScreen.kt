package com.vansh.foodappui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.foodappui.ui.theme.orange


@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        NavBar()
        SearchBar()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "All Categories",
                fontSize = 20.sp,
                color = Color.LightGray
            )
            Text(
                text = "See All",
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }
        ProductSection()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Open Restaurants",
                fontSize = 20.sp,
                color = Color.LightGray
            )
            Text(
                text = "See All",
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }
        RestaurantSection()
    }
}

@Composable
fun NavBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp),
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "Menu"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp),
            ) {
                Text(
                    text = "DELIVER TO",
                    fontWeight = FontWeight.Bold,
                    color = orange
                )
                Text(
                    text = "Vansh Office",
                    color = Color.Gray
                )
            }
        }
        BadgedBox(
            modifier = Modifier.padding(end = 10.dp),
            badge = {
                Badge{
                    Text(text = "2")
                }
            }
        ){
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        var text by remember {
            mutableStateOf("")
        }
        Text(
            text = buildAnnotatedString {
                append("Hey Vansh, ")
                withStyle(
                    SpanStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                ) {
                    append("Good Afternoon!")
                }
            },
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            label = {
                Text(
                    text = "Search dishes, restaurants"
                )
            }
        )
    }
}

