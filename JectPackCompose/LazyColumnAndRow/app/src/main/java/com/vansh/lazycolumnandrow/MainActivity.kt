package com.vansh.lazycolumnandrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vansh.lazycolumnandrow.ui.theme.LazyColumnAndRowTheme

class MainActivity : ComponentActivity() {

    companion object{
        val items: List<Item> = listOf(
            Item(
                title = "knight",
                image = R.drawable.knight
            ),
            Item(
                title = "fool",
                image = R.drawable.mrfool
            ),
            Item(
                title = "knight1",
                image = R.drawable.knight
            ),
            Item(
                title = "fool1",
                image = R.drawable.mrfool
            ),
            Item(
                title = "knight2",
                image = R.drawable.knight
            ),
            Item(
                title = "fool2",
                image = R.drawable.mrfool
            ),
            Item(
                title = "knight3",
                image = R.drawable.knight
            ),
            Item(
                title = "fool3",
                image = R.drawable.mrfool
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnAndRowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = "home_screen"
                    ){
                        composable("home_screen"){
                            HomeScreen(navHostController)
                        }
                        composable("lazy_row_screen"){
                            LazyRows()
                        }
                        composable("lazy_column_screen"){
                            LazyColumns()
                        }
                        composable("lazy_grid_screen"){
                            LazyGrids()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    navHostController: NavHostController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyButton({navHostController.navigate("lazy_row_screen")},"Lazy Row")
        MyButton({navHostController.navigate("lazy_column_screen")},"Lazy Column")
        MyButton({navHostController.navigate("lazy_grid_screen")},"Lazy Grid")
    }
}

@Composable
fun MyButton(
    onClick: () -> Unit,
    text: String
){
    Button(
        onClick = onClick,
    ) {
        Text(
            text = text
        )
    }
}
