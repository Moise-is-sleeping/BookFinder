package ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import ui.ViewModel.BookViewModel


@Composable
fun Screen(bookViewModel: BookViewModel){
    var text by remember { mutableStateOf("") }
    val list by bookViewModel.bookList.collectAsState()
    Column (modifier = Modifier.fillMaxSize()){
        LazyColumn(){
            items(list){
                item ->
                Row (modifier = Modifier){
                    Text(text = item!!.title.toString())
/*                    Text(text =item.covers.toString() )*/
                    Text(text = item.description.toString())
                }

            }
        }
        if (list.size>0){
            for (book in list){
                Row(modifier = Modifier) {
                    Text(text =book!!.title + " Publish date :" )
                }
            }

        }

        AsyncImage(model ="https://ia800505.us.archive.org/view_archive.php?archive=/25/items/m_covers_0010/m_covers_0010_97.zip&file=0010977514-l.jpg" , contentDescription ="test" )
        TextField(value = text, onValueChange ={text = it} )
        Button(onClick = {
            bookViewModel.searchBooksByName(text)
        }) {

        }

    }
}