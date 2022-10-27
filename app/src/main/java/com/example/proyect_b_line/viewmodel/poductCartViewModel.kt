package com.example.proyect_b_line.viewmodel

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_b_line.model.Product
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(){
    val recipes: MutableState<List<Product>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")


    fun newSearch(query: String){
        viewModelScope.launch {
            recipes.value
        }
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}



