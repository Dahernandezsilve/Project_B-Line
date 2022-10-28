package com.example.proyect_b_line.view.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyect_b_line.R
import com.example.proyect_b_line.viewmodel.SearchViewModel

val listCategories= listOf("Moda", "Nintendo", "Tecnología", "Carros")

@Composable
fun SearchView(viewModel:SearchViewModel) {
    val context = LocalContext.current
    Column {
        ConstraintLayout(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 5.dp)
                .fillMaxWidth(),
        ) {
            val (searchTextField, buttonFilters) = createRefs()
            createEndBarrier(searchTextField,buttonFilters)
            seach_TextField(
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
                onSearchChange = {viewModel.onQueryChanged(it)}
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
                onClick = { viewModel.onOpenFiltersChanged() },
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
        val OpenFilters by remember {
            viewModel.isOpenFilters
        }
        if(OpenFilters){
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.size(90.dp)) {

                }
            }
        }

        LazyRow(content = {
            for(store in listCategories){
                item {
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(5.dp, 5.dp)
                            .size(100.dp, 35.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp, Color(0x8DFFFFFF)),
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
fun seach_TextField(context:Context, modifier: Modifier, value:String, onSearchChange: (String)-> Unit){
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
        keyboardActions = KeyboardActions(onSearch = {
            Toast.makeText(
                context,
                value,
                Toast.LENGTH_LONG
            ).show()
        }),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.secondary)
        )
}




@Preview(showBackground = true)
@Composable
fun PrevSearch(){
    val viewModel:SearchViewModel = viewModel()
    SearchView(viewModel)
}