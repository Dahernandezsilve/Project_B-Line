package com.example.proyect_b_line.view.screens

import LoadingAnimation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyect_b_line.R
import com.example.proyect_b_line.viewmodel.SearchViewModel
import com.example.proyect_b_line.viewmodel.ViewmodelTonavigate

@Composable
fun StartLoading(
    viewModel: ViewmodelTonavigate,
    viewModelS: SearchViewModel,
    navController: NavHostController
){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .scale(viewModel.scales.value)
                .fillMaxSize()) {
            val mod: Modifier = Modifier.size(150.dp)
            Image(
                painter = painterResource(id = R.drawable.logosinborde),
                contentDescription = "Logo",
                modifier = mod
            )
            Image(
                painter = painterResource(id = R.drawable.textologo),
                contentDescription = "Logo"
            )
            LoadingAnimation(circleSize = 30.dp, circleColor = MaterialTheme.colors.primary)
            if(viewModelS.Start()){
                navController.navigate("main_screen")
            }
        }
    }

}