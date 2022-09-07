package com.example.proyect_b_line

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun productCart(url: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { }
            .size(150.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp)
    ){

    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(12.dp)){
            AsyncImage(model = url, contentDescription = "Hola texto", Modifier.requiredSize(125.dp))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    productCart(url = "https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg")
}