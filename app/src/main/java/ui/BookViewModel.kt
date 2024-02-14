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

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class BookViewModel: ViewModel() {

    private var bookApiService = BookApiService()
    var json by mutableStateOf("")

    var _list = MutableStateFlow<List<String>>(emptyList())
    var list: StateFlow<List<String>> = _list.asStateFlow()


    fun getBooks(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookApiService.getBook("OL46071324M")
            json = book.title
            Log.d("","")
        }
    }

    fun searchBooks(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList = mutableListOf<String>()
            val search = bookApiService.searchBook(search)
            for (doc in search.docs){
                tempList.add(doc.key)
            }
            _list.value = tempList
        }
    }
}




