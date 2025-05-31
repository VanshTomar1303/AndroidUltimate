package com.vansh.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    var state by mutableStateOf(Color.Red)
        private set

    fun changeBackgroundColor(){
        state = Color.Black
    }
}