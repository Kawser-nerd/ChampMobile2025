package com.example.navigationapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// safe args passing
@Composable
fun Settings(counter:String?) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Magenta),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(text = "Navigation with Arguments",
                Modifier.padding(10.dp),
                color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            // display the value
            Text(
                text = "Settings Screen, passed data is$counter",
                Modifier.padding(10.dp),
                color = Color.Black
            )
        }
    }
}