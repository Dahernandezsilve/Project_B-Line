package com.example.proyect_b_line.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.proyect_b_line.repository.get

@Composable
fun MainScreen(){
    LazyColumn(content = {
        for(product in get()){
            item{
                ProductCart(product = product)
            }
        }
    })
}






