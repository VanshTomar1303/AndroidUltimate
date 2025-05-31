package com.vansh.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vansh.viewmodel.ui.theme.ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTheme {
                val viewModel = viewModel<MyViewModel>(
                    factory = object : ViewModelProvider.Factory{
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return super.create(modelClass)
                        }
                    }
                )
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .background(viewModel.state)
                ) {
                    Button(
                        onClick = {
                            viewModel.changeBackgroundColor()
                        }
                    ) {
                        Text(
                            text = "Change Color"
                        )
                    }
                }
            }
        }
    }
}

/*
    ViewModels -> bridge b/w view and model
    '-> take raw data from model and change it to a easy readable data to the view.
    A ViewModel is a class designed to store and manage UI-related data in a lifecycle-conscious way.
    It is part of Android Jetpack Architecture Components.
    The main purpose of a ViewModel is to separate the UI logic from business logic and to persist data across configuration changes like screen rotations.

    One viewmodel per screen.
 */
