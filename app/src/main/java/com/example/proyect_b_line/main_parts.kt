package com.example.proyect_b_line

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.Card
import androidx.compose.ui.Modifier

@Composable
fun productCart(url: String){
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp)
            .clickable{ },
        elevation = 10.dp
    ){
    Column() {
            AsyncImage(model = url, contentDescription = "Hola texto")
        }

    }

}