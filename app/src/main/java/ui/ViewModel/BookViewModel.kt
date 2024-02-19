package ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Models.Works
import data.Util.BookApiService
import data.Util.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import ui.state.BookState
import ui.state.RatingsState
import ui.state.SearchBySubjectState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class BookViewModel: ViewModel() {

    private var bookApiService = BookApiService()
    private var bookRepository = BookRepository(bookApiService)

    val tempList2 = mutableListOf<Float>()

    private var  _homeBookList = MutableStateFlow<List<Works>>(emptyList())
    var homeBookList: StateFlow<List<Works>> = _homeBookList.asStateFlow()

    private var  _ratingList = MutableStateFlow<List<Float>>(emptyList())
    var ratingList: StateFlow<List<Float>> = _ratingList.asStateFlow()


    private var  _bookList = MutableStateFlow<List<BookState?>>(emptyList())
    var bookList: StateFlow<List<BookState?>> = _bookList.asStateFlow()



    fun getBooks(ids: MutableList<String>){
        viewModelScope.launch(Dispatchers.IO) {
            val tempList = mutableListOf<BookState?>()
            for (id in ids){
                val book = bookRepository.getBookState(id)
                tempList.add(book)
            }
            _bookList.value = tempList

        }
    }

    fun searchBooksByName(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchByNameState(search)
            val tempList = mutableListOf<String>()
            Log.d("results",result!!.docs.toString())
            for (doc in result.docs){
                val id = doc.key.substring(7)
                tempList.add(id)
            }

            getBooks(tempList)
        }
    }

    fun searchBooksBySubject(subject:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchBySubjectState(subject)
            val tempList = mutableListOf<Works>()
            val tempList1 = mutableListOf<String>()
            for (doc in result.works){
                tempList.add(doc)
                tempList1.add(doc.key.substring(7))
                getRatings(doc.key.substring(7))
            }
            _homeBookList.value = tempList

        }
    }


    fun getRatings(id:String){
        viewModelScope.launch(Dispatchers.IO) {
                val result = bookRepository.getRatings(id)
                tempList2.add(result.summary.average)
                Log.d("results",(result.summary.average.toString()))
            _ratingList.value = tempList2
        }
    }
    init {
        searchBooksBySubject("horror")
    }

    fun clear(){
        _bookList.value = emptyList()
    }




}




