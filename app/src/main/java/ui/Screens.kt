package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage


@Composable
fun Screen(bookViewModel:BookViewModel){
    var text by remember { mutableStateOf("Hello") }

    Column (modifier = Modifier.fillMaxSize()){
        Text(text = bookViewModel.json)
        AsyncImage(model ="https://ia600607.us.archive.org/view_archive.php?archive=/22/items/olcovers24/olcovers24-L.zip&file=240727-L.jpg" , contentDescription ="test" )
        TextField(value = text, onValueChange ={text = it} )
        Button(onClick = {
            bookViewModel.getBooks(text)
        }) {

        }

    }
}