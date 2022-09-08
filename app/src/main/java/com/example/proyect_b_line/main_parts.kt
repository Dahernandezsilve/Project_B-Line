package com.example.proyect_b_line

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.*
import coil.compose.AsyncImage
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

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

    Row( modifier = Modifier.padding(12.dp)){
            AsyncImage(model = url, contentDescription = "Hola texto", Modifier.requiredSize(125.dp))
            contentCard(producDescrip = "", disponibility = true, exists = 0 , score = 0.0f)
        }

    }

}

@Composable
fun contentCard(producDescrip:String, disponibility:Boolean, exists:Int, score:Float ){
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "HOLA MUNDO")
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
            Text(text = "Mas informacion del producto", fontSize = 9.sp)

        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    productCart(url = "https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg")
    //contentCard(producDescrip = "", disponibility = true, exists =0 , score =0.0f )
}