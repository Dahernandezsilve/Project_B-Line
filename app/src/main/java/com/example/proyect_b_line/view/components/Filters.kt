package com.example.proyect_b_line.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyect_b_line.R
import com.example.proyect_b_line.model.Categories
import com.example.proyect_b_line.viewmodel.SearchViewModel

@Composable
fun Filters(categorie:String, onChageCategorie:(String)->Unit, optionable:Boolean, onChageOptionable:()->Unit, categories: List<Categories>,
            sizeInt: Int,
            rotater: Float

) {

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .size(sizeInt.dp)){
        val (options, cardCategorie, cardPrice, optionPriceMinimun, optionPrice) = createRefs()

        Card(
            Modifier
                .fillMaxWidth()
                .size(25.dp)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(15.dp)
                )
                .constrainAs(cardCategorie) {
                    top.linkTo(parent.top, margin = 10.dp)
                    absoluteLeft.linkTo(parent.absoluteLeft)
                    absoluteRight.linkTo(parent.absoluteRight)
                }

        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (textDescription, textOption, iconButton) = createRefs()
                createAbsoluteLeftBarrier(textOption,iconButton, margin = 2.dp)
                Text(
                    text = "  Categorias  ",
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxHeight()
                        .constrainAs(textDescription) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            absoluteLeft.linkTo(parent.absoluteLeft)
                        }
                )
                      /*OutlinedTextField(
                          value = categorie,
                          onValueChange = onChageCategorie,
                          modifier = Modifier.rer
                      )*/
                Text(
                    text = categorie,
                    modifier = Modifier.constrainAs(textOption){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        absoluteRight.linkTo(iconButton.absoluteLeft, 1.dp)
                        absoluteLeft.linkTo(textDescription.absoluteRight, 1.dp)
                    }

                )
                IconButton(onClick = onChageOptionable, modifier = Modifier.constrainAs(iconButton){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    absoluteRight.linkTo(parent.absoluteRight)
                }) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.arrow_down_float),
                        contentDescription ="",
                        modifier = Modifier.rotate(rotater)
                    )
                }
            }
        }
        if(optionable){
            Options(categories = categories, modifier = Modifier.constrainAs(options){
                top.linkTo(cardCategorie.bottom)
                absoluteRight.linkTo(parent.absoluteRight)
                absoluteLeft.linkTo(parent.absoluteLeft)
            })
        }
    }
}

@Composable
fun Rango(categories:List<Categories>, modifier: Modifier){
    Card(modifier = modifier
        .size(90.dp, 50.dp)) {
        LazyColumn{
            for (categori in categories){
                item {
                    TextButton(onClick = categori.onChange, modifier = Modifier.fillMaxWidth()) {
                        Text(text = categori.option, modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp))
                    }
                }
            }

        }
    }

}

@Composable
fun Options(categories:List<Categories>, modifier: Modifier){
    Card(modifier = modifier
        .fillMaxWidth()
        .size(50.dp)) {
        LazyColumn{
            for (categori in categories){
                item {
                    TextButton(onClick = categori.onChange, modifier = Modifier.fillMaxWidth()) {
                        Text(text = categori.option, modifier = Modifier
                            .size(90.dp,20.dp), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevFilters(){

    val viewModel: SearchViewModel = viewModel()

    Filters(viewModel.categorie.value, { viewModel.onChangeCategorie(it) }, viewModel.optionable.value,{viewModel.onChangeOptionable()}, viewModel.categories().value!!,viewModel.sizeInt.value, viewModel.rotater.value)
}