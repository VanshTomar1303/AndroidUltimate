package com.vansh.foodappui.model

import androidx.annotation.DrawableRes
import com.vansh.foodappui.R

data class Product(
    val name: String,
    val price: Float,
    @DrawableRes val image: Int
){
    companion object{
        val products: List<Product> = listOf(
            Product(
                name = "Burger",
                price = 50f,
                image = R.drawable.burger
            ),
            Product(
                name = "Pizza",
                price = 50f,
                image = R.drawable.pizza
            ),
            Product(
                name = "Burger",
                price = 50f,
                image = R.drawable.burger
            ),
            Product(
                name = "Pizza",
                price = 50f,
                image = R.drawable.pizza
            ),Product(
                name = "Burger",
                price = 50f,
                image = R.drawable.burger
            ),
            Product(
                name = "Pizza",
                price = 50f,
                image = R.drawable.pizza
            ),
        )
    }
}
