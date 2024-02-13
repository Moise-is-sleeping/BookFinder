package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun Screen(bookViewModel:BookViewModel){
    bookViewModel.getBooks()
    Column (modifier = Modifier.fillMaxSize()){
        Text(text = bookViewModel.json)
    }
}