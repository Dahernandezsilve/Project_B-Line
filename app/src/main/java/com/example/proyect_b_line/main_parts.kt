package com.example.proyect_b_line

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import coil.compose.AsyncImage

@Composable
fun productCart(url: String){
    Box{
        AsyncImage(model = url, contentDescription = "Hola texto")
    }

}