package com.example.proyect_b_line.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.proyect_b_line.repository.getProducts

@Composable
fun MainScreen(){
    LazyColumn(content = {
        for(product in getProducts()){
            item{
                ProductCart(product = product)
            }
        }
    })
}






