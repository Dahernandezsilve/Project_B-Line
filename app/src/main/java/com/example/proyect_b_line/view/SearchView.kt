package com.example.proyect_b_line.view

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView() {

        var value by remember { mutableStateOf("") }
        val modifier:Modifier =Modifier.size(280.dp,55.dp)
        Row(
            modifier = Modifier.padding(5.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = value,
                onValueChange ={value=it},
                placeholder = {
                    Text(text = " Buscar",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = modifier,
                shape = RoundedCornerShape(200.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "", modifier = Modifier.padding(5.dp))
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.secondary)
            )
            Button(modifier = Modifier
                .padding(10.dp, 0.dp)
                .size(70.dp, 40.dp), onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {

            }
        }




}

@Preview(showBackground = true)
@Composable
fun PrevSearch(){
    SearchView()
}