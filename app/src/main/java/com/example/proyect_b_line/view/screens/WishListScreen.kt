package com.example.proyect_b_line.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyect_b_line.R
import com.example.proyect_b_line.ui.theme.PB_Theme
import com.example.proyect_b_line.view.components.Stores
import com.example.proyect_b_line.view.components.Header
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

@Composable
fun WishListScreen (){
    PB_Theme {
        Column {
            Header(R.drawable.milista)
            val viewModel: SearchViewModel = viewModel()
            Stores(mutableListOf(), viewModel = viewModel, true)
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val welcomeText = createRef()
                Text(modifier = Modifier.constrainAs(welcomeText){
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    absoluteRight.linkTo(parent.absoluteRight)
                    absoluteLeft.linkTo(parent.absoluteLeft)

                }, textAlign = TextAlign.Center, text = " + Añade elementos para verlos aquí!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PPreview(){
    WishListScreen()
}
