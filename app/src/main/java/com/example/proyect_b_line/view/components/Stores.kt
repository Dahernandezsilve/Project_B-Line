package com.example.proyect_b_line.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

val listStores= listOf("Amazon", "Ebay", "Guatemala digital", "MarketPlace")

@Composable
fun Stores(productsList: MutableList<Product>?, viewModel: SearchViewModel, initialValue: Boolean, navController: NavController) {
    val context = LocalContext.current
    Column {
        LazyRow(content = {
            for(store in listStores){
                item {
                    TextButton(
                        onClick = {
                            navController.navigate("AmazonScreen")
                                  },
                        modifier = Modifier
                            .shadow(15.dp, shape = RoundedCornerShape(15.dp,15.dp,0.dp,0.dp), ambientColor = MaterialTheme.colorScheme.inverseSurface)
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
        val iniTVal:Int = when(initialValue){
            true->R.drawable.estrella2
            false->R.drawable.estrella
        }
        val seconVal:Int = when(initialValue){
            true->R.drawable.estrella
            false->R.drawable.estrella2
        }
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

    }
}

@Preview(showBackground = true)
@Composable
fun PrevStores(){
    
}