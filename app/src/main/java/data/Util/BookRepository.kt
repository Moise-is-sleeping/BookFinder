package data.Util

import android.util.Log
import data.Models.Author
import data.Models.Book
import data.Models.Ratings
import data.Models.SearchByName
import data.Models.SearchBySubject
import retrofit2.Response
import ui.state.AuthorState
import ui.state.BookState
import ui.state.RatingsState
import ui.state.SearchByNameState
import ui.state.SearchBySubjectState

class BookRepository(private val bookapi : BookApiService) {


    suspend fun searchByNameState(search:String): SearchByNameState {
        val response = bookapi.searchBooksByName(search)
        return if (response.isSuccessful){
            response.body()?.toSearchByNameState()?: SearchByNameState()
        }else{
            SearchByNameState()
        }
    }

    suspend fun getAuthor(id:String): AuthorState {
        val response = bookapi.getAuthor(id)
        return if (response.isSuccessful){
            response.body()?.toAuthorState()?:AuthorState()
        }
        else{
            AuthorState()
        }
    }


    suspend fun searchBySubjectState(subject:String): SearchBySubjectState {
        val response1 = bookapi.searchBooksBySubject(subject)
        if (response1.isSuccessful){
            return response1.body()?.toSearchBySubjectState()?:SearchBySubjectState() }
        else{
                return SearchBySubjectState()
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

    suspend fun getRatings(id:String): RatingsState {
        val response = bookapi.getRatings(id)
        return if(response.isSuccessful){
            response.body()?.toRatingsSate()?: RatingsState()
        }else{
            RatingsState()
        }
    }

    private fun Author.toAuthorState():AuthorState{
        return AuthorState(
            name = this.name
        )
    }
    private fun Ratings.toRatingsSate(): RatingsState {
        return RatingsState(
            summary = this.summary
        )
    }
    private fun SearchByName.toSearchByNameState(): SearchByNameState {
        return SearchByNameState(
            docs = this.docs,
            numFound = this.numFound
        )

    }

    private fun SearchBySubject.toSearchBySubjectState(): SearchBySubjectState {
        return SearchBySubjectState(
            works = this.works,


        )
    }
    private fun Book.toBookSate(): BookState {
        return BookState(
            title = this.title,
            covers = this.covers,
            subjects = this.subjects,
            links = this.links,
            type = this.type,
            description = this.description,
            authors = this.authors
        )
    }
}