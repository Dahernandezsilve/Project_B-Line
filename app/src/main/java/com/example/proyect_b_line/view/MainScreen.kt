package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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






