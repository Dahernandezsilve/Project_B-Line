package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.proyect_b_line.repository.getProducts
import com.example.proyect_b_line.ui.theme.PB_Theme

@Composable
fun MainScreen(){
    PB_Theme {
        Column {
            SearchView()
            Stores()
        }
    }
}






