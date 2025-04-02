package com.example.navigationapp

sealed class Routes(val route:String) { // this class is going to work with controller
    // this is a enum type class, that works with Kotilin interface files
    data object Home : Routes("home")
    data object Profile : Routes("profile")
    data object Settings : Routes("setting")
}