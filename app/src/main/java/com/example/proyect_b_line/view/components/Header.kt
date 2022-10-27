package com.example.proyect_b_line.view.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.FabPosition.Companion.Center
import androidx.compose.material3.FabPosition.Companion.Center
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.proyect_b_line.R
import com.example.proyect_b_line.view.SplashScreen
import com.example.proyect_b_line.view.TextInCard
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun Header(iconId: Int) {
    Column{
        Row (modifier = Modifier.fillMaxWidth().padding(0.dp,5.dp), Arrangement.Center, ){
            Image(modifier = Modifier.size(67.dp,61.dp), painter = painterResource(id = R.drawable.logosinborde), contentDescription = "Logo" )
            Image(modifier = Modifier.size(67.dp, 61.dp), painter = painterResource(id = iconId), contentDescription = "texto logo" )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HPreview(){
    Header(R.drawable.logosinborde)
}
