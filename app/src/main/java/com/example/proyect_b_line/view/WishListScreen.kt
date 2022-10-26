package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.proyect_b_line.ui.theme.PB_Theme

@Composable
fun WishListScreen (navController: NavController){
    PB_Theme {
        Column {
            Stores(navController)
        }

    }
}