package com.example.proyect_b_line.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.getProducts
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(){
    private val productList = MutableLiveData<List<Product>>(listOf())

    fun productList():LiveData<List<Product>> = productList

    fun newSearch(query: String){
        viewModelScope.launch {
            productList.postValue(getProducts())
        }
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



