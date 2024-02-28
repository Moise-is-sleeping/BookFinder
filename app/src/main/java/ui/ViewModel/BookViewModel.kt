package ui.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Models.Authors
import data.Models.Doc
import data.Models.Works
import data.Util.BookApiService
import data.Util.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import ui.state.AuthorState
import ui.state.BookState
import ui.state.RatingsState
import ui.state.SearchByNameState
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


    private var  _bookList = MutableStateFlow<List<Doc?>>(emptyList())
    var bookList: StateFlow<List<Doc?>> = _bookList.asStateFlow()

    private var  _searchValue = MutableStateFlow<String>("")
    var searchValue: StateFlow<String> = _searchValue.asStateFlow()

    private var  _hasSearched = MutableStateFlow(false)
    var hasSearched: StateFlow<Boolean> = _hasSearched.asStateFlow()

    private var  _hasCovers = MutableStateFlow(true)
    var hasCovers: StateFlow<Boolean> = _hasCovers.asStateFlow()

    private var  _horrorBookList = MutableStateFlow<List<Works>>(emptyList())
    var horrorBookList: StateFlow<List<Works>> = _horrorBookList.asStateFlow()
    private var  _romanceBookList = MutableStateFlow<List<Works>>(emptyList())
    var romanceBookList: StateFlow<List<Works>> = _romanceBookList.asStateFlow()
    private var  _sciFiBookList = MutableStateFlow<List<Works>>(emptyList())
    var sciFiBookList: StateFlow<List<Works>> = _sciFiBookList.asStateFlow()
    private var  _novelBookList = MutableStateFlow<List<Works>>(emptyList())
    var novelBookList: StateFlow<List<Works>> = _novelBookList.asStateFlow()
    private var  _mysteryBookList = MutableStateFlow<List<Works>>(emptyList())
    var mysteryBookList: StateFlow<List<Works>> = _mysteryBookList.asStateFlow()

    val currentBookId = mutableStateOf("")
    var _bookDetails = MutableStateFlow(BookState())
    var bookDetails : StateFlow<BookState> = _bookDetails.asStateFlow()
    private var _author = MutableStateFlow(AuthorState())
    var author : StateFlow<AuthorState> = _author.asStateFlow()

    fun getBooks(id:String){
        currentBookId.value = id
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookRepository.getBookState(id)
            try {
                book.covers!![0].toString()
            }catch (e:Exception){
                _hasCovers.value = false
            }
            _bookDetails.value = book

            if (!book.authors.toString().isNullOrBlank()){
                getAuthor(extractId(book.authors.toString()))
            }else{
                _author.value = AuthorState("Unknown")
            }
        }
    }

    fun extractDescription(value: String): String {
        return if (value.indexOf("{") !=-1){
            value.substring(24,value.length-1)
        }
        else{
            value
        }
    }
    fun extractId(value:String): String {
        val end = value.indexOf(",")
        return value.substring(23,end-1)
    }

    fun getAuthor(id:String){
        viewModelScope.launch {
            val result = bookRepository.getAuthor(id)
            _author.value = result
        }

    }

    fun searchBooksByName(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchByNameState(search)
            Log.d("serachValue",(search))
            _bookList.value = result.docs
        }
    }

    fun searchBooksBySubject(subject:String,value:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchBySubjectState(subject)
            val tempList = mutableListOf<Works>()
            for (doc in result.works){
                tempList.add(doc)
                Log.d("serachValue",doc.title.toString())
                if (value == 0){
                    getRatings(doc.key.substring(7))
                }
            }
            when(value){
                0 -> _homeBookList.value = tempList
                1 -> _horrorBookList.value = tempList
                2 -> _romanceBookList.value = tempList
                3 -> _sciFiBookList.value = tempList
                4 -> _novelBookList.value = tempList
                5 -> _mysteryBookList.value = tempList
            }

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
    fun hasSearched(){
        _hasSearched.value = true
    }
    fun hasNotSearched(){
        _searchValue.value = ""
        _bookList.value = emptyList()
        _hasSearched.value = false
    }
    fun nullDates(date:String): String {
        if (date.isNullOrEmpty()){
            return "unknown"
        }else{
            return date
        }
    }
    fun updateSearchValue(newSearchValue:String){
        _searchValue.value = newSearchValue
    }

    fun backgroundColor(): Long {
        if (_hasSearched.value){
            return 0xFFFFFFFF
        }else{
            return 0xFFE5DBD0
        }
    }
    fun resetHasCovers(){
        _bookDetails.value = BookState()
        _hasCovers.value = true
    }

    init {
        searchBooksBySubject("fantasy",0)
        searchBooksBySubject("horror",1)
        searchBooksBySubject("love",2)
        searchBooksBySubject("dystopias",3)
        searchBooksBySubject("novel",4)
        searchBooksBySubject("mystery",5)
    }

    fun clear(){
        _bookList.value = emptyList()
    }




}




