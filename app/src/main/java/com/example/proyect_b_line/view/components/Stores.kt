package com.example.proyect_b_line.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyect_b_line.R
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.viewmodel.SearchViewModel

/**
 * Proyect-Bline
 * Desarrollado por HexaTeamBlue
 *
 * Description:
 * Diseño de la interfaz mediante la que se muestran los distints elementos
 * obtenidos de las tiendas en linea
 * @author Daniel Valdez, Diego Hernandez, Javier Alvarado
 * @since 0.1.2, november 6th 2022
 */

@Composable
fun Stores(productsList: MutableList<Product>?, viewModel: SearchViewModel, initialValue: Boolean) {
    val context = LocalContext.current
    Column {
        if (viewModel.changeStores.value){
            LazyRow(content = {
                for(store in viewModel.listStores){
                    item {
                        TextButton(
                            onClick = {
                                viewModel.changeStore(store)
                                      },
                            modifier = Modifier
                                .shadow(
                                    15.dp,
                                    shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
                                    ambientColor = MaterialTheme.colorScheme.inverseSurface
                                )
                                .padding(2.dp, 0.dp)
                                .size(120.dp, 35.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp),
                            content ={
                                Text(text = store, style = MaterialTheme.typography.bodyLarge)
                            }
                        )
                    }
                }
            })
        }
        val iniTVal:Int = when(initialValue){
            true->R.drawable.estrella2
            false->R.drawable.estrella
        }
        val seconVal:Int = when(initialValue){
            true->R.drawable.estrella
            false->R.drawable.estrella2
        }
        if(!viewModel.erroQuery.value){
            if (!viewModel.changeList.value){
                LazyColumn(content = {
                    if (productsList != null) {
                        for(i in productsList.indices){
                            item{
        
                                ProductCart(product = productsList[i],viewModel.obtainPainter(i, iniTVal), {viewModel.changeBoolean(i,iniTVal, seconVal)})
                            }
                        }
                    }
                }
                )
            }else{
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, modifier = Modifier
                    .fillMaxSize()
                    .padding(92.dp),
                    strokeWidth = 20.dp
                    )
            }
        }else{
            Text(text = "Error, no se encontraron elementos con dicha busqueda o no se logro conectar a la pagina", style = MaterialTheme.typography.bodyMedium)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PrevStores(){
    val viewModel:SearchViewModel = viewModel()
    Stores(productsList = viewModel.productListB.value, viewModel = viewModel, initialValue = false)
}