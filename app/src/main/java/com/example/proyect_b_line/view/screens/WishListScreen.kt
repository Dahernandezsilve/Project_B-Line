package com.example.proyect_b_line.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.proyect_b_line.R
import com.example.proyect_b_line.ui.theme.PB_Theme
import com.example.proyect_b_line.view.Stores
import com.example.proyect_b_line.view.components.Header

@Composable
fun WishListScreen (){
    PB_Theme {
        Column {
            Header(R.drawable.milista)
            Stores(listOf())

            //Hola
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
