package com.example.proyect_b_line.view

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyect_b_line.R
import com.example.proyect_b_line.ui.theme.gilroyFont
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ProductCart(url: String, producDescrip:String, disponibility:Boolean, exists:Int, score:Float, sendly:Boolean, costSend:Float, costProduct:Float =0.0f ){
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { }
            .size(150.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp)
    ){
        Column(verticalArrangement = Arrangement.Center, modifier= Modifier.padding(10.dp)) {
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(text =producDescrip, fontFamily = gilroyFont, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold)
                Text(text=("$"+df.format(costProduct)), fontFamily = gilroyFont, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold)
            }
            Row(
                modifier = Modifier
                    .border(shape = RoundedCornerShape(10.dp), border = BorderStroke(0.dp, Color.White)),
                verticalAlignment = Alignment.CenterVertically
            ){

                AsyncImage(model = url,
                    contentDescription = "Hola texto",
                    Modifier.requiredSize(95.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Crop)

                ContentCard(disponibility, exists, score, sendly, costSend)
            }
        }
    }
}

@Composable
fun ContentCard(disponibility: Boolean, exists: Int, score: Float, sendly: Boolean, costSend: Float){
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        TextInCard(disponibility,exists,score, sendly, costSend)
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
                Text(text = "Mas informacion del producto", fontSize = 10.sp, fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFE9AC13))
        )
    }
    
}

@Composable
fun TextInCard(disponibility: Boolean, exists: Int, score: Float, sendly: Boolean, costSend: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier=Modifier.width(120.dp)
        ) {
            Disponibility(disponibility = disponibility)
            Send(sendly = sendly, costSend = costSend)
        }
        Column(modifier=Modifier.width(120.dp)) {
            Existence(exists)
            Calification(score = score)
        }
    }
    
}

@Composable
fun Disponibility(disponibility: Boolean){
    var icon:Int = R.drawable.notdisponibility
    if (disponibility){
        icon= R.drawable.disponibility
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "- Disponibilidad",
            textAlign = TextAlign.Left,
            fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp,
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
            fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
        Text(text = ""+exists,
            textAlign = TextAlign.Right,
            modifier = Modifier.padding(8.dp, 8.dp)
                .size(40.dp, 13.dp),
            fontFamily = gilroyFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 10.sp
            )

    }
}
@Composable
fun Calification(score:Float){
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "- Calificación",
            textAlign = TextAlign.Left,
            fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
        Text(text = ""+df.format(score),
            textAlign = TextAlign.End,
            modifier = Modifier.padding(8.dp),
            fontFamily = gilroyFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 10.sp
            )

    }
}


@Composable
fun Send(sendly: Boolean, costSend: Float){
    var icon:Int = R.drawable.notdisponibility
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    var cost:String = ""
    if (sendly){
        icon= R.drawable.disponibility
        cost="$"+df.format(costSend)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), verticalAlignment = Alignment.CenterVertically){
        Row(modifier = Modifier.width(90.dp)){
            Text(
                text = "- Envío ",
                textAlign = TextAlign.Left,
                fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
            Text(
                text = cost,
                textAlign = TextAlign.Left,
                fontFamily = gilroyFont, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
        }

        Image(painter = painterResource(id = icon), contentDescription ="", modifier = Modifier.padding(5.dp), Alignment.CenterEnd)

    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ProductCart(url = "https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg",
        "Audifonos",
        true,
        9,
        4.4f,
        true,
        3.4f,
        100.0f
    )
    //contentCard(producDescrip = "", disponibility = true, exists =0 , score =0.0f )
}
