package com.example.jetpackcompseexp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompseexp.ui.theme.JetPackCompseExpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompseExpTheme { // unit function: helps to compose the UI
/*
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting( // this is the main design function
                        name = "Nafi",
                        modifier = Modifier.padding(innerPadding)


                    )

 */
                Surface (
                    color = MaterialTheme.colorScheme.background
                ){

                    FirstApp("Kawser")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text( // textwidget
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun FirstApp(name:String){ // First UI function
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
        { // this is the place where we are going to define what
            // do we want to put inside the colum
        Text(
            text = "Happy Day $name",
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            color = Color.Cyan,
            fontSize = 40.sp,
            //fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.5.sp,
            textDecoration = TextDecoration.None,
            textAlign = TextAlign.Center,
            onTextLayout = {
                textLayoutResult:TextLayoutResult ->
                val lineCount = textLayoutResult.lineCount
                println("$lineCount")
            },

            style = TextStyle(
                background = Color.Black,
                shadow = Shadow(color = Color.Gray, blurRadius = 40f)
            )

        ) // until this we configured Text
         // additional space
        Spacer(modifier = Modifier.height(20.dp) )
            // Button Configuration
        val context = LocalContext.current

            Button(
            onClick = {
                Toast.makeText(context,
                    "Button is pressed",
                    Toast.LENGTH_LONG
                    ).show()
            },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Cyan
                ),
                interactionSource = remember { MutableInteractionSource() }

        ) { // inside the button
                Text( // text visible on button
                    text = "Show",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackCompseExpTheme {
       // Greeting("Android")
        FirstApp("Nafi")
    }
}