package com.example.proyect_b_line.view.components

import LoadingAnimation
import android.text.Layout.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
                item {
                    Row(
                        modifier = Modifier
                            .border(
                                0.dp,
                                shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
                                color = MaterialTheme.colorScheme.secondary
                            )
                            .padding(2.dp, 0.dp)
                            .size(120.dp, 35.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                            ),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = viewModel.listStores[0],
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                }

                for(store in 1.. (viewModel.listStores.size-1)){
                    item {
                        TextButton(
                            onClick = {
                                viewModel.changeStore(viewModel.listStores[store])
                                viewModel.obtainProducts()
                                      },
                            modifier = Modifier
                                .padding(2.dp, 0.dp)
                                .size(120.dp, 35.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(0.dp,0.dp,0.dp,0.dp),
                            content ={
                                Text(text = viewModel.listStores[store], style = MaterialTheme.typography.bodyLarge)
                            }
                        )
                    }
                }
            },
                modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary)
            )
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
        
                                ProductCart(product = productsList[i],viewModel.obtainPainter(i, iniTVal, !initialValue), {viewModel.changeBoolean(i,iniTVal, seconVal, !initialValue)})
                            }
                        }
                    }
                }
                )
            }else{
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val loadingAnimation = createRef()
                    LoadingAnimation(circleColor = MaterialTheme.colorScheme.primary, modifier = Modifier.constrainAs(loadingAnimation){
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                        absoluteRight.linkTo(parent.absoluteRight)
                        absoluteLeft.linkTo(parent.absoluteLeft)
                    },
                        circleSize = 30.dp
                        )

                }

            }
        }else{
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val errorText = createRef()
                Text(modifier = Modifier.constrainAs(errorText){
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    absoluteRight.linkTo(parent.absoluteRight)
                    absoluteLeft.linkTo(parent.absoluteLeft)

                }, textAlign = TextAlign.Center, text = " !! No hemos encontrado elementos con esta busqueda")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PrevStores(){
    val viewModel:SearchViewModel = viewModel()
    Stores(productsList = viewModel.productListB.value, viewModel = viewModel, initialValue = false)
}