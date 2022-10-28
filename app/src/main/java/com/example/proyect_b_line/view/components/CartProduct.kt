package com.example.proyect_b_line.view.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.proyect_b_line.R
import com.example.proyect_b_line.model.Product
import java.math.RoundingMode
import java.text.DecimalFormat


@Composable
fun ProductCart(product:Product){
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    var start by remember {
        mutableStateOf(false)
    }
    var painterRe by remember {
        mutableStateOf(R.drawable.estrella)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { }
            .size(150.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = MaterialTheme.colorScheme.secondary
    ){
        Column(verticalArrangement = Arrangement.Center, modifier= Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (productName, productPrice, buttonStart) = createRefs()
                createStartBarrier(productName,productPrice)
                createAbsoluteLeftBarrier(buttonStart,productName, margin = 3.dp)
                Text(text =product.product_Description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.constrainAs(productName){
                        top.linkTo(parent.top, margin = 3.dp)
                        absoluteLeft.linkTo(parent.absoluteLeft, margin= 3.dp)
                    }
                )
                IconButton(onClick = {
                    start = !start
                    when(start){
                        true -> {
                            painterRe = R.drawable.estrella
                        }
                        false->{ painterRe = R.drawable.estrella2
                        }

                    }
                                     }, modifier = Modifier
                    .constrainAs(buttonStart) {
                        top.linkTo(parent.top, margin = 3.dp)
                        absoluteLeft.linkTo(productName.absoluteRight, margin = 3.dp)
                    }
                    .size(20.dp),
                    content = { Image(painter = painterResource(id = painterRe), contentDescription ="" ) }

                )

                Text(
                    text=("$"+df.format(product.costProduct)),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.constrainAs(productPrice){
                        top.linkTo(parent.top, margin = 3.dp)
                        absoluteRight.linkTo(parent.absoluteRight, margin = 3.dp)
                    }
                )
            }
            /*Row(modifier=Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(text =product.product_Description, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
                Text(text=("$"+df.format(product.costProduct)), style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
            }*/
            Row(
                modifier = Modifier
                    .border(shape = RoundedCornerShape(10.dp), border = BorderStroke(0.dp, Color.Transparent)),
                verticalAlignment = Alignment.CenterVertically
            ){
                Card(shape = RoundedCornerShape(15.dp), modifier = Modifier
                    .requiredSize(95.dp)
                    .padding(5.dp)) {
                    AsyncImage(model = product.urlImage,
                        contentDescription = product.product_Description,
                        Modifier
                            .padding(5.dp)
                            .requiredSize(96.dp)
                        )
                }


                ContentCard(product.availability, product.exists, product.score, product.shippable, product.costSend)
            }
        }
    }
}

@Composable
fun ContentCard(availability: Boolean, exists: Int, score: Float, shippable: Boolean, costSend: Float){
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        TextInCard(availability,exists,score, shippable, costSend)
        Button(onClick = {
            Toast.makeText(
                context,
                "Showing toast....",
                Toast.LENGTH_LONG
            ).show()
        },modifier = Modifier
            .fillMaxWidth()
            .height(27.dp),
            content = {
                Text(text = "Mas informacion del producto", fontSize = 10.sp, style = MaterialTheme.typography.bodyLarge)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        )
    }

}

@Composable
fun TextInCard(availability: Boolean, exists: Int, score: Float, shippable: Boolean, costSend: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier=Modifier.width(120.dp)
        ) {
            Availability(availability = availability)
            Send(shippable = shippable, costSend = costSend)
        }
        Column(modifier=Modifier.width(120.dp)) {
            Existence(exists)
            Qualification(score = score)
        }
    }

}

@Composable
fun Availability(availability: Boolean){
    var icon:Int = R.drawable.not_availability
    if (availability){
        icon= R.drawable.availability
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "- Disponibilidad",
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodyLarge, fontSize = 10.sp,
            modifier = Modifier.width(90.dp)
        )
        Image(painter = painterResource(id = icon), contentDescription ="", modifier = Modifier.padding(5.dp))

    }
}
@Composable
fun Existence(exists: Int){

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "- Existencia",
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodyLarge, fontSize = 10.sp)
        Text(text = ""+exists,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .size(40.dp, 13.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 10.sp
            )

    }
}
@Composable
fun Qualification(score:Float){
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "- Calificación",
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodyLarge, fontSize = 10.sp)
        Text(text = ""+df.format(score),
            textAlign = TextAlign.End,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 10.sp
            )

    }
}


@Composable
fun Send(shippable: Boolean, costSend: Float){
    var icon:Int = R.drawable.not_availability
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    var cost = ""
    if (shippable){
        icon= R.drawable.availability
        cost="$"+df.format(costSend)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), verticalAlignment = Alignment.CenterVertically){
        Row(modifier = Modifier.width(90.dp)){
            Text(
                text = "- "+"Envío"+" ",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge, fontSize = 10.sp)
            Text(
                text = cost,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge, fontSize = 10.sp)
        }

        Image(painter = painterResource(id = icon), contentDescription ="", modifier = Modifier.padding(5.dp), Alignment.CenterEnd)

    }
}

