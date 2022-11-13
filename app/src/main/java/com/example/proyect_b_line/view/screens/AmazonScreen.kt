package com.example.proyect_b_line.view.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyect_b_line.R
import com.example.proyect_b_line.ui.theme.PB_Theme
import com.example.proyect_b_line.view.components.Stores
import com.example.proyect_b_line.view.components.Header
import com.example.proyect_b_line.view.components.SearchViewEbay
import com.example.proyect_b_line.view.components.SearchViewAmazon
import com.example.proyect_b_line.viewmodel.SearchViewModel

/**
 * *Proyect-Bline
 * Desarrollado por HexaTeamBlue*
 *
 * Description:
 * Diseño principal de la lista de deseos de la aplicación
 * @author Daniel Valdez, Diego Hernandez, Javier Alvarado
 * @since 0.1.2, november 6th 2022
 */

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AmazonScreen(navController: NavController){
    PB_Theme {
        Column (modifier = Modifier)  {
            val viewModel: SearchViewModel = viewModel()
            Header(R.drawable.textologo)
            SearchViewAmazon(viewModel)
            Stores(viewModel.productListB.value!!, viewModel, true,navController)
        }
    }
}
