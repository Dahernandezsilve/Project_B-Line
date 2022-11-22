package com.example.proyect_b_line.view.components

import android.content.Context
import android.os.AsyncTask
import android.os.Build
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

val listCategories= listOf("Moda", "Nintendo", "Tecnología", "Carros")

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchView(viewModel:SearchViewModel) {
    val context = LocalContext.current
    var expandedFilter by remember {
        mutableStateOf(false)
    }
    Column {
        ConstraintLayout(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 5.dp)
                .fillMaxWidth(),
        ) {
            val (searchTextField, buttonFilters) = createRefs()
            createEndBarrier(searchTextField,buttonFilters)
            Search_TextField(
                context=context,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .size(50.dp)
                    .constrainAs(searchTextField)
                    {
                        top.linkTo(parent.top, margin = 1.dp)
                        bottom.linkTo(parent.bottom, margin = 1.dp)
                        absoluteLeft.linkTo(parent.absoluteLeft, margin = 5.dp)
                    },
                value = viewModel.query.value,
                onSearchChange = {viewModel.onQueryChanged(it)},
                onSearch = {viewModel.searchStore(context)
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.20f)
                    .size(40.dp)
                    .constrainAs(buttonFilters) {
                        top.linkTo(parent.top, margin = 3.dp)
                        bottom.linkTo(parent.bottom, margin = 3.dp)
                        absoluteRight.linkTo(parent.absoluteRight, margin = 5.dp)
                    },
                onClick = { expandedFilter = !expandedFilter},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.filtrar),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )



        }
        Surface{
            AnimatedContent(
                targetState = expandedFilter,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) { targetExpanded ->
                if (targetExpanded) {
                    Filters(viewModel.categorie.value, { viewModel.onChangeCategorie(it) }, viewModel.optionable.value,{viewModel.onChangeOptionable()}, viewModel.categories().value!!,viewModel.sizeInt.value, viewModel.rotater.value )}
            }
        }
        LazyRow(content = {
            for(store in listCategories){
                item {
                    TextButton(
                        onClick = {
                            viewModel.searchCategorie(context)
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