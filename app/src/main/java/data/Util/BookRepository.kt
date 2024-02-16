package data.Util

import data.Models.Book
import data.Models.Search
import data.Util.BookApiService
import ui.state.BookState
import ui.state.SearchState

class BookRepository(private val bookapi : BookApiService) {


    suspend fun searchBooksState(search:String): SearchState {
        val response = bookapi.searchBook(search)
        return if (response.isSuccessful){
            response.body()?.toSearchState()?: SearchState()
        }else{
            SearchState()
        }
    }
    suspend fun getBookState(id:String): BookState {
        val response = bookapi.getBook(id)
        return if(response.isSuccessful){
            response.body()?.toBookSate()?: BookState()
        }else{
            BookState()
        }
    }


    private fun Search.toSearchState(): SearchState {
        return SearchState(
            docs = this.docs,
            numFound = this.numFound
        )

    }
    private fun Book.toBookSate(): BookState {
        return BookState(
            title = this.title,
            covers = this.covers,
            subjects = this.subjects,
            links = this.links,
            type = this.type,
            description = this.description
        )
    }
}