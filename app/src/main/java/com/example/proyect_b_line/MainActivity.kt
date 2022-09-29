package com.example.proyect_b_line


import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.proyect_b_line.R
import com.example.proyect_b_line.productCart
import com.example.proyect_b_line.ui.theme.Proyect_BLineTheme
import kotlinx.coroutines.delay

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
            LazyColumn(content = {
                item {
                    productCart(url = "https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg",
                        "Audifonos L",
                        false,
                        19,
                        2.4232323f,
                        false,
                        433.49004f,
                        13.0f
                    )
                }
                item {
                    productCart(url = "https://i.pinimg.com/564x/df/0c/86/df0c86955745151d0291e698e9b2d528.jpg",
                        "Pintura de Lobazo",
                        false,
                        29,
                        2.4232323f,
                        true,
                        2333.49004f,
                        23.0f
                    )
                }
                item {
                    productCart(url = "https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/41PSrh5lpdL._AC_SX466_.jpg",
                        "Joi Cons",
                        false,
                        12,
                        4.4232323f,
                        false,
                        433.49004f,
                        50.0f
                    )
                }
                item {
                    productCart(url = "https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/71lcyYiN9rL._AC_UY218_.jpg",
                        "Nintendo Wii usada",
                        true,
                        49,
                        2.4232323f,
                        false,
                        433.49004f,
                        100.0f
                    )
                }
                item {
                    productCart(url = "https://images-na.ssl-images-amazon.com/images/W/WEBP_402378-T1/images/I/31uagjqD8DL._SX300_SY300_QL70_FMwebp_.jpg",
                        "Kit de VR",
                        true,
                        49,
                        2.4232323f,
                        true,
                        93.49004f,
                        10000.0f
                    )
                }
            })




        }
    }
}
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        // Change the logo
        animLOGO(scale = scale)

    }
}

@Composable
fun animLOGO(scale: Animatable<Float, AnimationVector1D>){
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.scale(scale.value)) {
            val mod:Modifier = Modifier.size(150.dp)
            Image(
                painter = painterResource(id = R.drawable.logosinborde),
                contentDescription = "Logo",
                modifier = mod
            )
            Image(
                painter = painterResource(id = R.drawable.textologo),
                contentDescription = "Logo"
            )
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold() {
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Navigation()
            }
        }
    }
}