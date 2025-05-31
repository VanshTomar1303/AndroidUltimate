package com.vansh.expensetracker.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.expensetracker.R
import com.vansh.expensetracker.ui.theme.dimWhite
import com.vansh.expensetracker.ui.theme.greenish
import com.vansh.expensetracker.ui.theme.lightGreenish


@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dimWhite)
                .padding(innerPadding)
        ){
            Image(
                painter = painterResource(R.drawable.ic_topbar),
                contentDescription = "TopBarBackground",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopStart
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 20.dp)
            ) {
                Column {
                    Text(
                        text = "Good Morning!",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Vansh Tomar",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 130.dp, start = 16.dp, end = 16.dp)
                    .shadow(20.dp, spotColor = Color.Green)
                    .clip(RoundedCornerShape(20.dp))
                    .background(greenish)
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ){
                        Text(
                            text = "Total Balance",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "$ 2345.03",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IncomeAndExpenseDetails(
                            "Income",
                            "$ 245.03",
                            R.drawable.income
                        )
                        IncomeAndExpenseDetails(
                            "Expense",
                            "$ 2465.03",
                            R.drawable.expense
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp, bottom = 46.dp)
            ) {
                Text(
                    text = "Transactions History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn(
                    modifier = Modifier
                ) {
                    items {item ->
                        IncomeAndExpenseItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun IncomeAndExpenseItem(x0: Int) {

}

@Composable
fun IncomeAndExpenseDetails(
    type: String,
    amount: String,
    image: Int
) {
    Column{
        Row {
            Image(
                painter = painterResource(image),
                contentDescription = type,
                modifier = Modifier.size(25.dp)
                    .clip(CircleShape)
                    .background(lightGreenish)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = type,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = amount,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}