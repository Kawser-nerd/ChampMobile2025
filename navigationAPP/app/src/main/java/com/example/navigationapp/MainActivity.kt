package com.example.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.screens.Home
import com.example.navigationapp.screens.Profile
import com.example.navigationapp.screens.Settings
import com.example.navigationapp.ui.theme.NavigationAPPTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAPPTheme (dynamicColor = false, darkTheme = false) {
                Surface (color = Color.White) {
                    ScreenMain()
                    NavigationDrawerApp()
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

// Create Navigation Drawer UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerApp(){
    // boolean state variable to find out whether the drawer is active/ in-active
    val drawState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()// this will help us to get the current active controller
    // the following function will provide you the layout for your navigation drawer/menu
    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, drawState)
        },
        drawerState = drawState
    ) { // UI components
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    navigationIcon = { // menu bar icon
                        IconButton(
                            onClick = {scope.launch { drawState.open() }}
                        ) {
                            Icon(
                                Icons.Default.Menu, contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { // body of scaffold layout
                paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ){
                NavigationGraph(navController)
            }
        }
    }
}
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState){
    ModalDrawerSheet { // the drawer page
        Spacer(modifier = Modifier.height(16.dp))

        val scope = rememberCoroutineScope()
        Column(
          modifier = Modifier.fillMaxHeight().padding(16.dp)
        ) {
            // contents for the drawer
            listOf("Home", "Profile", "Settings").forEach{ screen ->
                Text(
                    text = screen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().clickable {
                        navController.navigate(screen.lowercase())
                        scope.launch { drawerState.close() }
                    }.padding(12.dp)
                )

            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ScreenContent("Home Screen")}
        composable("profile"){ScreenContent("Profile Screen")}
        composable("settings"){ScreenContent("Settings Screen")}
    }
}
@Composable
fun ScreenContent(text:String){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ){
         Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationAPPTheme {
        ScreenMain()
        NavigationDrawerApp()
    }
}