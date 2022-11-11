package com.example.proyect_b_line.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyect_b_line.R
/**
 * *Proyect-Bline
 * Desarrollado por HexaTeamBlue*
 *
 * Description:
 * Encabezado de la aplicación, su función es puramente estetica y para hacer
 * reconocible la marca de la aplicación o la finalidad de la pantalla.
 *
 * @author Daniel Valdez, Diego Hernandez, Javier Alvarado

 * @since 0.1.2, november 6th 2022
 */
@Composable
fun Header(iconId: Int) {
    Column{
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp), Arrangement.Center){
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
