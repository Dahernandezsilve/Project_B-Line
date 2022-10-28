package com.example.proyect_b_line.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    val recipes: MutableState<List<Product>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    var isOpenFilters:MutableState<Boolean> = mutableStateOf(false)

    var text: String = ""
    fun newSearch(query: String, context: Context){

        viewModelScope.launch(Dispatchers.IO) {
            recipes.value
            text = getDataWithJsoup(query, context)
        }
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }



    fun onOpenFiltersChanged(){
        isOpenFilters.value!=isOpenFilters.value
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}



