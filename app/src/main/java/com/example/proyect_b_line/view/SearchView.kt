package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.proyect_b_line.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView() {

        var value by remember { mutableStateOf("") }

        ConstraintLayout(
            modifier = Modifier.padding(0.dp, 10.dp).fillMaxWidth(),
        ) {
            val (searchTextField, buttonFilters) = createRefs()
            createEndBarrier(searchTextField,buttonFilters)

            OutlinedTextField(singleLine = true, value = value,
                onValueChange ={value=it},
                maxLines = 1,
                modifier = Modifier.size(300.dp, 50.dp).constrainAs(searchTextField){
                    top.linkTo(parent.top, margin= 3.dp)
                    bottom.linkTo(parent.bottom, margin = 3.dp)
                    absoluteLeft.linkTo(parent.absoluteLeft, margin = 5.dp)
                },
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
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.secondary)
            )
            Button(modifier = Modifier
                .size(70.dp, 40.dp)
                .constrainAs(buttonFilters){
                  top.linkTo(parent.top, margin= 3.dp)
                  bottom.linkTo(parent.bottom, margin = 3.dp)
                  absoluteRight.linkTo(parent.absoluteRight, margin = 5.dp)
                }
                , onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                content = {
                  Icon(painter = painterResource(id = R.drawable.filtrar), contentDescription = "" )
                }
                ) 
        }
}

@Preview(showBackground = true)
@Composable
fun PrevSearch(){
    SearchView()
}