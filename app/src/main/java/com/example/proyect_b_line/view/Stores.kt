package com.example.proyect_b_line.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyect_b_line.repository.getProducts

val listStores= listOf("Amazon", "Ebay", "Guatemala digital", "MarketPlace")
val listCategories= listOf("Moda", "Nintendo", "Tecnología", "Carros")
@Composable
fun Stores() {
    Column {
        LazyRow(content = {
            for(store in listCategories){
                item {
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(5.dp,5.dp)
                            .size(100.dp, 35.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp, Color(0x8DFFFFFF)),
                        content ={
                            Text(text = store, style = MaterialTheme.typography.bodyLarge, fontSize = 12.sp)
                        }
                    )
                }
            }
        })
        LazyRow(content = {
            for(store in listStores){
                item { 
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(2.dp,0.dp)
                            .size(120.dp, 35.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp),
                        content ={
                            Text(text = store, style = MaterialTheme.typography.bodyLarge)
                        }
                    )
                }
            }
        })
        LazyColumn(content = {
            for(product in getProducts()){
                item{
                    ProductCart(product = product)
                }
            }
        })
    }
    
    
}

@Preview(showBackground = true)
@Composable
fun PrevStores(){
    
}