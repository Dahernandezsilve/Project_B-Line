package com.example.proyect_b_line.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
 * @since 0.5.0, november 23th 2022
 */

@Composable
fun WishListScreen (navController: NavController, viewModel: SearchViewModel){
    viewModel.changeList.value=true
    PB_Theme {
        Column {
            Header(R.drawable.milista)
            viewModel.obtainProducts()
            Stores(viewModel.productListFavorite, viewModel = viewModel, true)
            viewModel.changeList.value=false
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val welcomeText = createRef()

                if (viewModel.productListFavorite.size == 0){
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
}

//@Preview(showBackground = true)
//@Composable
//fun PPreview(){
//    WishListScreen()
//}
