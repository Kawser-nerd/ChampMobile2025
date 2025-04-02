package com.example.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.screens.Home
import com.example.navigationapp.screens.Profile
import com.example.navigationapp.screens.Settings
import com.example.navigationapp.ui.theme.NavigationAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAPPTheme (dynamicColor = false, darkTheme = false) {
                Surface (color = Color.White) {
                    ScreenMain()
                }
            }
        }
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/
@Composable
fun ScreenMain(){
    // create the object of the navigation Controller
    val navController = rememberNavController()
    NavHost( // this is the host class, where you need to pass all the
        // pages going to be navigated
        navController = navController,
        startDestination = Routes.Home.route){
        //Home page route
        composable(Routes.Home.route) {
            Home(navController = navController)
        }
        // Profile
        composable(Routes.Profile.route){
            Profile()
        }
        // settings with safe argument passing
        composable(Routes.Settings.route + "/{id}" ){
            // find out the value passed
            navBackStack ->
                val couter = navBackStack.arguments?.getString("id")
                Settings(counter = couter)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationAPPTheme {
        ScreenMain()
    }
}