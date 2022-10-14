package com.example.proyect_b_line.view

import SearchView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.proyect_b_line.repository.getProducts
import com.example.proyect_b_line.ui.theme.Proyect_BLineTheme

@Composable
fun MainScreen(){
    Proyect_BLineTheme {
        Column {
            SearchView()
            LazyColumn(content = {
                for(product in getProducts()){
                    item{
                        ProductCart(product = product)
                    }
                }
            })
        }
    }
}






