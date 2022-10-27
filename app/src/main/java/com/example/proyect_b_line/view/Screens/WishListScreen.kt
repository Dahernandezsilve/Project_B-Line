package com.example.proyect_b_line.view

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyect_b_line.ui.theme.PB_Theme

@Composable
fun WishListScreen (){
    PB_Theme {
        Column() {
            Stores(listOf())
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val welcomeText = createRef()
                Text(modifier = Modifier.constrainAs(welcomeText){
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    absoluteRight.linkTo(parent.absoluteRight)
                    absoluteLeft.linkTo(parent.absoluteLeft)

                }, textAlign = TextAlign.Center, text = " + Añade elementos para verlos aca!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PPreview(){
    WishListScreen()
}
