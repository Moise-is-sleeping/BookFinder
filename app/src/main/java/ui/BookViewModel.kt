package ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.BookApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {

    private var bookApiService = BookApiService()
    var json by mutableStateOf("")

    fun getBooks(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookApiService.getBook("OL46071324M")
            json = book.title
            Log.d("","")
        }
    }
}