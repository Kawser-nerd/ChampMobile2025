package com.example.navigationapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigationapp.Routes

@Composable
fun Home(navController: NavController){ // this is the landing page, so navController needs to get access this function
    var counter by remember {
       mutableIntStateOf(0)
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Cyan),
        contentAlignment = Alignment.Center

    ){
        Column {
            Text(text = "Home, Counter is $counter", color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {counter++}
            ) {
                Text(text = "Increment Counter", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            // navigate to profile screen
            Button(onClick = {
                navController.navigate(Routes.Profile.route)
            }) {
                Text(text = "Navigate to Profile", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            // navigate to the Settings Screen
            Button(onClick = {
                //navController.navigate("${Routes.Settings.route}/$counter")
                navController.navigate(Routes.Settings.route + "/$counter")
            }) {
                Text(text = "Navigate to Settings", color = Color.White)
            }
        }
    }
}