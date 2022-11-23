package com.example.proyect_b_line.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Categories
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.KeyStore.TrustedCertificateEntry


class SearchViewModel(current: Context) : ViewModel(){
    val listStores= mutableStateListOf("Amazon", "Ebay", "Guatemala digital", "MarketPlace")
    val listCategories= mutableStateListOf("Moda", "Videojuegos", "Tecnología", "Carros")
    var rotater = mutableStateOf(0.0f)
    val dbFavorites=DBHandler(current)
    val dicStores= mutableStateMapOf<String, MutableList<Product>>()
    var productListFavorite= mutableStateListOf<Product>()
    fun Start():Boolean{
        try {
            dicStores.put("Amazon", getProducts())
        }catch (exeception:NumberFormatException){
            return false
        }
        try {
            dicStores.put("Guatemala Digital", getProducts())
        }catch (exeception:NumberFormatException){
            return false
        }
        try {
            dicStores.put("Marketplace", getProducts())
        }catch (exeception:NumberFormatException){
            return false
        }
        return true
    }

    private val categories = MutableLiveData(
        listOf(Categories("Tecnologias", {onChangeCategorie("Tecnologias")}),Categories("Alimentos", {onChangeCategorie("Alimentos")}),Categories("Videojuegos", {onChangeCategorie("Videojuegos")})
        ))//, "Alimentos", "Videojuegos"))
    fun categories():LiveData<List<Categories>> = categories

    var ranger = mutableStateOf("")

    var sizeInt = mutableStateOf(45)

    private val rangers = MutableLiveData(
        listOf(Categories("$200", {onChangeCategorie("$200")}),Categories("$500", {onChangeCategorie("$500")}),Categories("$1000", {onChangeCategorie("$1000")})
        ))
    fun addFavorite(i:Int, inB: Boolean){
        lateinit var product:Product
        if(inB){
            product=productListB.value[i]
        }else product=productListFavorite[i]
        viewModelScope.launch {
            dbFavorites.addNewCourse(listStores[0],
                product.Url, product.urlImage,
                product.product_Description,
                product.availability,
                product.exists,
                product.score,
                product.shippable,
                product.costSend,
                product.costProduct
            )
            productListFavorite.add(product)
        }

    }

    fun deleteFavorite(i:Int, inB: Boolean){
        viewModelScope.launch {
            lateinit var product:Product
            if(inB){
                product=productListB.value[i]
            }else{
                product=productListFavorite[i]
            }
            val arg: Array<String> = arrayOf(product.Url)
            dbFavorites.deleteFavorite(arg)
            productListFavorite.remove(product)
        }

    }



    var text: String = ""
    var optionable = mutableStateOf(false)

    var categorie = mutableStateOf("")


    fun onChangeCategorie(categorieButton:String){
        categorie.value =categorieButton
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


    @SuppressLint("MutableCollectionMutableState")
    val productListB = mutableStateOf(getProducts())

    fun searchStore(context: Context){
        val stores = listStores[0]
        paintersFavorite = mutableStateListOf<Int>()
        firstBool = mutableStateListOf<Boolean>()
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

    fun searchCategorie(context: Context, categorie:String){
        when(categorie){
            "Moda"->newSearchModa()
            "Deportes"->newSearchDeportes()
            "Videojuegos"->newSearchVideojuegos()
            "Tecnología"->newSearchTecnologies()
            "Salud"->newSearchSalud()
            "Arte"->newSearchArte()
            "Software"->newSearchSoftware()
        }
    }

    val changeList= mutableStateOf( false)
    val erroQuery = mutableStateOf(false)
    fun newSearchEbay(){
        val query = this.query
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithJsoupEbay(query.value)

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else {
                    productListB.value=listTotry}
            }
            changeList.value = false
        }


    }

    fun newSearchAmazon(){
        val query = this.query
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithJsoupAmazon(query.value)

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchModa(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieModa()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchVideojuegos(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieVideojuegos()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchTecnologies(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieTecnologies()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchDeportes(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieDeportes()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchSalud(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieSalud()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchArte(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieArte()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    fun newSearchSoftware(){
        changeList.value = true
        erroQuery.value = false
        viewModelScope.launch(Dispatchers.IO) {
            var listTotry = getProducts()
            try {
                listTotry = getDataWithCategorieSoftware()

            }catch(exeption:NumberFormatException) {
                erroQuery.value=true
            }

            if(listTotry == null){
                productListB.value= getProducts()
                erroQuery.value=true
            }else{
                if (listTotry.size==0){
                    productListB.value= getProducts()
                    erroQuery.value=true
                }else productListB.value=listTotry
            }
            changeList.value = false
        }
    }

    val query = mutableStateOf("")

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    val booleanFavorite = mutableListOf<Boolean>()

    var paintersFavorite = mutableStateListOf<Int>()
    var firstBool = mutableStateListOf<Boolean>()
    fun obtainPainter(i:Int, initalValue:Int, init:Boolean):Int{

        if(i>=paintersFavorite.size){
            paintersFavorite.add(initalValue)
            firstBool.add(!init)
        }

        return paintersFavorite[i]
    }

    var booleanLoop= mutableStateOf(true)


    fun changeBoolean(i:Int, initalPaint:Int, secondPaint: Int, initalValue: Boolean){

        firstBool[i]= when(firstBool[i]){
            true -> false
            false ->true
        }

        if (firstBool[i]){
            addFavorite(i,initalValue)
        }else{
            deleteFavorite(i,initalValue)
        }


        paintersFavorite[i]= when(paintersFavorite[i]){
            initalPaint->secondPaint
            secondPaint->initalPaint
            else -> secondPaint
        }


    }
    fun obtainProducts():MutableList<Product>{
        val arg = arrayOf(listStores[0])
        paintersFavorite = mutableStateListOf<Int>()
        firstBool = mutableStateListOf<Boolean>()
        productListFavorite=dbFavorites.readEspecificFavorites(arg)!!.let {
            mutableStateListOf()
        }
        return productListFavorite

    }

    val changeStores = mutableStateOf(true)

    fun changeStore(storeToChange:String){
        changeStores.value = false
        changeList.value=true
        productListB.value = getProducts()
        paintersFavorite = mutableStateListOf<Int>()
        firstBool = mutableStateListOf<Boolean>()
        val actualStore:String = listStores[0]
        val indiceStore:Int = listStores.indexOf(storeToChange)
        listStores[0]= listStores[indiceStore]
        listStores[indiceStore]=actualStore
        changeStores.value = true
        changeList.value=false

    }

    val changeCategorie = mutableStateOf(true)

    fun changeCategorie(storeToChange:String){
        changeCategorie.value = false
        changeList.value=true
        productListB.value = getProducts()
        val actualCategorie:String = listCategories[0]
        val indiceCategorie:Int = listCategories.indexOf(storeToChange)
        listCategories[0]= listCategories[indiceCategorie]
        listCategories[indiceCategorie]=actualCategorie
        changeCategorie.value = true
        changeList.value=false

    }

}