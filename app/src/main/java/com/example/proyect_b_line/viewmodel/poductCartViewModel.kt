package com.example.proyect_b_line.viewmodel

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Categories
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.getDataWithJsoupAmazon
import com.example.proyect_b_line.repository.getDataWithJsoupEbay
import com.example.proyect_b_line.repository.getProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel: ViewModel(){
    val listStores= mutableStateListOf("Amazon", "Ebay", "Guatemala digital", "MarketPlace")

    var rotater = mutableStateOf(0.0f)

    private val categories = MutableLiveData(
        listOf(Categories("Tecnologias", {onChangeCategorie("Tecnologias")}),Categories("Alimentos", {onChangeCategorie("Alimentos")}),Categories("Videojuegos", {onChangeCategorie("Videojuegos")})
        ))//, "Alimentos", "Videojuegos"))
    fun categories():LiveData<List<Categories>> = categories

    var ranger = mutableStateOf("")

    var sizeInt = mutableStateOf(45)

    private val rangers = MutableLiveData(
        listOf(Categories("$200", {onChangeCategorie("$200")}),Categories("$500", {onChangeCategorie("$500")}),Categories("$1000", {onChangeCategorie("$1000")})
        ))
    fun rangers():LiveData<List<Categories>> = rangers

    var text: String = ""
    var optionable = mutableStateOf(false)

    var categorie = mutableStateOf("")

    fun onChagesize(){
        if(sizeInt.value<=45){
            sizeInt.value=75
        }else{
            sizeInt.value=45
        }

    }

    fun onChangeCategorie(categorieButton:String){
        categorie.value =categorieButton
    }
    fun onChangeRager(categorieButton:String){
        ranger.value = categorieButton
    }

    fun onChangeOptionable(){
        optionable.value = !optionable.value
        if(sizeInt.value<=45){
            sizeInt.value=100
            rotater.value = 180.0f
        }else{
            sizeInt.value=45
            rotater.value = 0.0f
        }
    }


    val productListB = mutableStateOf(getProducts())

    fun searchStore(context: Context){
        val stores = listStores[0]

        when(stores){

            "Amazon"->newSearchAmazon()
            "Ebay"->{
                newSearchEbay()
            }
            else -> {
                Toast.makeText(context, stores, Toast.LENGTH_LONG).show()
            }
        }
    }

    val changeList= mutableStateOf( false)
    val erroQuery = mutableStateOf(false)
    fun newSearchEbay(){
        val query = this.query
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productListB.value = getDataWithJsoupEbay(query.value)
            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }
            if(productListB.value.size==0){
                erroQuery.value=true
            }
            changeList.value = false
        }


    }

    fun newSearchAmazon(){
        val query = this.query
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val listTotry = getDataWithJsoupAmazon(query.value)
                if(listTotry != null){
                    if(listTotry.size!=0){
                        productListB.value = listTotry
                    }
                }
            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(productListB.value.size==0){
                erroQuery.value=true
            }
            changeList.value = false
        }
    }

    val query = mutableStateOf("")

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun newList(mutableList: MutableList<Product>){


    }


    var mol= mutableListOf("", "")
    val booleanFavorite = mutableListOf<Boolean>()

    fun obtainBooleanAsigned(i:Int, initalValue:Boolean):Boolean{

        if(i>=booleanFavorite.size){
            booleanFavorite.add(initalValue)
        }
        return booleanFavorite[i]
    }
    val paintersFavorite = mutableStateListOf<Int>()
    fun obtainPainter(i:Int, initalValue:Int):Int{

        if(i>=paintersFavorite.size){
            paintersFavorite.add(initalValue)
        }
        return paintersFavorite[i]
    }

    fun changeBoolean(i:Int, initalPaint:Int, secondPaint: Int){
        paintersFavorite[i]= when(paintersFavorite[i]){
            initalPaint->secondPaint
            secondPaint->initalPaint
            else -> secondPaint
        }
    }

    val changeStores = mutableStateOf(true)

    fun changeStore(storeToChange:String){
        changeStores.value = false
        val actualStore:String = listStores[0]
        val indiceStore:Int = listStores.indexOf(storeToChange)
        listStores[0]= listStores[indiceStore]
        listStores[indiceStore]=actualStore
        changeStores.value = true
    }

}