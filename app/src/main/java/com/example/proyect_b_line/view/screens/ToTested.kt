package com.example.proyect_b_line.view.screens

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyect_b_line.R
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.DBHandler
import com.example.proyect_b_line.repository.getProducts
import com.example.proyect_b_line.view.components.ProductCart
import com.example.proyect_b_line.viewmodel.SearchViewModel
import kotlin.random.Random

@Composable
fun ToTested(){
    val toTester = getProducts()
    val context = LocalContext.current
    val dbHandler = DBHandler(context)
    var h:List<Product>? = null
    var j by remember {
        mutableStateOf(false)
    }
    val arr:Array<String> = arrayOf("Amazon")
    val lisStore = listOf<String>("Amazon", "Ebay")
    Row {
        Button(onClick = {
            dbHandler.deleteTable()
            for (i in 0.. (toTester.size-1)){
                dbHandler.addNewCourse(lisStore.get(Random.nextInt(0,2)),
                    toTester[i].Url, toTester[i].urlImage,
                    toTester[i].product_Description,
                    toTester[i].availability,
                    toTester[i].exists,
                    toTester[i].score,
                    toTester[i].shippable,
                    toTester[i].costSend,
                    toTester[i].costProduct
                )}
        }){
        }

        Button(onClick = {h=dbHandler.readEspecificFavorites(arr)}){}
        Button(onClick = {j = !j}){}


        }
    LazyColumn(){
        if (j){
            if(h!=null){
                for(k in 0.. (h!!.size-1)){
                    item {
                        ProductCart(product = h!![k], painterRe = R.drawable.estrella){

                        }
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun PrevSearch(){
    ToTested()
}