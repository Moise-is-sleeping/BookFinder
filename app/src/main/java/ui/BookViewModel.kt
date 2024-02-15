package ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.BookApiService
import data.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class BookViewModel: ViewModel() {

    private var bookApiService = BookApiService()
    private var bookRepository = BookRepository(bookApiService)
    var json by mutableStateOf("")

    private var  _list = MutableStateFlow<List<String>>(emptyList())
    var list: StateFlow<List<String>> = _list.asStateFlow()


    fun getBooks(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookApiService.getBook(id)

        }
    }

    fun searchBooks(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchBooksState(search)
            val tempList = mutableListOf<String>()
            Log.d("results",result!!.docs.toString())
            for (doc in result.docs){
                val id = doc.key.substring(7)
                tempList.add(id)
            }
            _list.value = tempList
//            getAllBooks(_list)
        }
    }
    fun getAllBooks(books: MutableStateFlow<List<String>>){
        for (i in 0..20){
            try {
                getBooks("OL1718419W")
            }catch (e:Exception){

            }

        }
    }



}




