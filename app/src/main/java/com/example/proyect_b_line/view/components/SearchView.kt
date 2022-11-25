package com.example.proyect_b_line.view.components

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyect_b_line.R
import com.example.proyect_b_line.model.Product
import com.example.proyect_b_line.repository.getDataWithJsoupAmazon
import com.example.proyect_b_line.repository.getProducts
import com.example.proyect_b_line.repository.listToUsersAgents
import com.example.proyect_b_line.viewmodel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import kotlin.random.Random

/**
 * Proyect-Bline
 * Desarrollado por HexaTeamBlue
 *
 * Description:
 * Maneja todos los elementos de busqueda de la aplicación.
 * @author Daniel Valdez, Diego Hernandez, Javier Alvarado
 * @since 0.1.2, november 6th 2022
 */

val listCategories= listOf("Moda","Deportes", "Videojuegos", "Tecnología", "Salud", "Arte", "Software")

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SearchView(viewModel:SearchViewModel) {
    val context = LocalContext.current
    val window =(context as Activity).findViewById<View>(android.R.id.content)
    Column {

            Search_TextField(
                context=context,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(10.dp,0.dp)
                ,
                value = viewModel.query.value,
                onSearchChange = {viewModel.onQueryChanged(it)},
                onSearch = {viewModel.searchStore(context)
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        window.applicationWindowToken,
                        0
                    )

                }
            )
        LazyRow(content = {
            for(store in listCategories){
                item {
                    TextButton(
                        onClick = {
                            viewModel.searchCategorie(context,store)
                        },
                        modifier = Modifier
                            .padding(5.dp, 5.dp)
                            .size(100.dp, 35.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(15.dp),
                        content ={
                            Text(text = store, style = MaterialTheme.typography.bodyLarge, fontSize = 12.sp)
                        }
                    )
                }
            }
        }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search_TextField(context:Context, modifier: Modifier, value:String, onSearchChange: (String)-> Unit, onSearch: (KeyboardActionScope.() -> Unit)?){
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onSearchChange,
        maxLines = 1,
        modifier = modifier,
        placeholder = {
            Text(text = " Buscar",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxSize()
            )
        },

        shape = RoundedCornerShape(200.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "", modifier = Modifier.padding(9.dp, 5.dp))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,

        ),
        keyboardActions = KeyboardActions(onSearch = onSearch),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.secondary)
        )
}





@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun PrevSearch(){
    val viewModel:SearchViewModel = viewModel()
    CircularProgressIndicator()
}