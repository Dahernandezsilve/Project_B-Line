package com.example.proyect_b_line.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.getDataWithJsoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel: ViewModel(){
    private val productList = MutableLiveData<List<Product>>(listOf())

    fun productList():LiveData<List<Product>> = productList
    var text: String = ""
    fun newSearch(query: String, context: Context){

        viewModelScope.launch(Dispatchers.IO) {
            productList.value
            text = getDataWithJsoup(query, context)
        }
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }



    val query = mutableStateOf("")

    private val isOpenFilters = MutableLiveData(false)

    fun isOpenFilters():LiveData<Boolean> = isOpenFilters

    fun onOpenFiltersChanged(){
        isOpenFilters.postValue(isOpenFilters.value)
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}



