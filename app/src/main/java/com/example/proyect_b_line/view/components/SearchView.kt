package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            modifier = Modifier.padding(5.dp, 10.dp).fillMaxWidth(),
        ) {
            val (searchTextField, buttonFilters) = createRefs()
            createEndBarrier(searchTextField,buttonFilters)
            OutlinedTextField(value = value,
                onValueChange ={value=it},
                placeholder = {
                    Text(text = " Buscar",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = Modifier.constrainAs(searchTextField){
                 top.linkTo(parent.top, margin = 3.dp)
                 absoluteLeft.linkTo(parent.absoluteLeft, margin = 3.dp)
                 absoluteRight.linkTo(buttonFilters.absoluteLeft, margin = 2.dp)
                },
                shape = RoundedCornerShape(200.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "", modifier = Modifier.padding(5.dp))
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.secondary)
            )
            Button(modifier = Modifier
                .size(70.dp, 40.dp)
                .constrainAs(buttonFilters){
                  top.linkTo(parent.top, margin= 9.dp)
                  absoluteRight.linkTo(parent.absoluteRight, margin = 3.dp)
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