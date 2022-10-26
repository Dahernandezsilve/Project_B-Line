package com.example.proyect_b_line.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.proyect_b_line.R
import com.example.proyect_b_line.ui.theme.PB_Theme

@Composable
fun MainScreen(navController: NavController){
    PB_Theme {
        Column (modifier = Modifier) {
            SearchView()
            Stores(navController)

        }
    }
}






