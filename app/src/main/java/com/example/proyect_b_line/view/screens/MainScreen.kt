package com.example.proyect_b_line.view.screens
/**
 * Proyect-Bline
 * Desarrollado por HexaTeamBlue
 * @author Daniel Valdez, Diego Hernandez, Javier Alvarado
 * Description:
 * Parte del programa que se encarga de manejar la pantalla principal
 * de la aplicación
 * @version 0.1.2, november 6th 2022
 */
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyect_b_line.R
import com.example.proyect_b_line.repository.getProducts
import com.example.proyect_b_line.ui.theme.PB_Theme
import com.example.proyect_b_line.view.components.Header
import com.example.proyect_b_line.view.components.SearchView
import com.example.proyect_b_line.view.components.Stores
import com.example.proyect_b_line.viewmodel.SearchViewModel


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(navController: NavController){
    val viewModel:SearchViewModel = viewModel()
    PB_Theme {
        Box{
            Column (modifier = Modifier) {
                Header(R.drawable.textologo)
                SearchView(viewModel)
                Stores(viewModel.productList().value!!, viewModel)
            }
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val floatingButton = createRef()
                FloatingActionButton(onClick = {
                    navController.navigate("wish_list_screen")
                }, containerColor = MaterialTheme.colorScheme.secondary, modifier = Modifier
                    .constrainAs(floatingButton) {
                        bottom.linkTo(parent.bottom, margin = 15.dp)
                        absoluteRight.linkTo(parent.absoluteRight, margin = 15.dp)
                    }
                    .size(75.dp, 75.dp))
                {
                    Image(painter = painterResource(id = R.drawable.b_line_logo), contentDescription = "B-Line" )
                }

            }

        }

    }
}






