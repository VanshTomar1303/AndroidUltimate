package com.vansh.foodappui.model

import androidx.annotation.DrawableRes
import com.vansh.foodappui.R

data class Restaurant(
    @DrawableRes val image: Int,
    val name: String,
    val dishes: List<String>,
    val rating: Float,
    val time: Int,
    val delivery: String
){
    companion object{
        val restaurants: List<Restaurant> = listOf(
            Restaurant(
                image = R.drawable.res1,
                name = "Rose Garden Restaurant",
                dishes = listOf("Burger","Chicken"),
                rating = 4.7f,
                time = 20,
                delivery = "Free"
            ),
            Restaurant(
                image = R.drawable.res2,
                name = "Centrum Restaurant",
                dishes = listOf("Burger","Pizza"),
                rating = 4.9f,
                time = 10,
                delivery = "Free"
            ),
            Restaurant(
                image = R.drawable.res1,
                name = "Rose Garden Restaurant",
                dishes = listOf("Burger","Chicken"),
                rating = 4.7f,
                time = 20,
                delivery = "Free"
            ),
            Restaurant(
                image = R.drawable.res2,
                name = "Centrum Restaurant",
                dishes = listOf("Burger","Pizza"),
                rating = 4.9f,
                time = 10,
                delivery = "Free"
            ),
        )
    }
}
