package com.example.proyect_b_line.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyect_b_line.ui.theme.Proyect_BLineTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyect_BLineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        Navigation()
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        // Main Screen
       // composable("main_screen") {
         //   Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
           //     Text(text = "Main Screen", color = Color.Black, fontSize = 24.sp)
           // }
        //}
        composable("main_screen"){
            MainScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Navigation()
            }
        }
    }
}