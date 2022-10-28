package com.example.proyect_b_line.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Categories
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.getDataWithJsoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(){
    private val productList = MutableLiveData<List<Product>>(listOf())
    fun productList():LiveData<List<Product>> = productList

    private val categories = MutableLiveData<List<Categories>>(
        listOf(Categories("Tecnologias", {onChangeCategorie("Tecnologias")}),Categories("Alimentos", {onChangeCategorie("Alimentos")}),Categories("Videojuegos", {onChangeCategorie("Videojuegos")})
        ))//, "Alimentos", "Videojuegos"))
    fun categories():LiveData<List<Categories>> = categories

    var ranger = mutableStateOf("")

    var sizeInt = mutableStateOf(45)

    private val rangers = MutableLiveData<List<Categories>>(
        listOf(Categories("$200", {onChangeCategorie("$200")}),Categories("$500", {onChangeCategorie("$500")}),Categories("$1000", {onChangeCategorie("$1000")})
        ))//, "Alimentos", "Videojuegos"))
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
            sizeInt.value=75
        }else{
            sizeInt.value=45
        }
    }

    fun newSearch(query: String, context: Context){

        viewModelScope.launch(Dispatchers.IO) {
            productList.value
            text = getDataWithJsoup()
        }
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }



    val query = mutableStateOf("")

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}



