package ui

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

    fun getBooks(){
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookApiService.getBook()
            json = book.title
        }
    }
}