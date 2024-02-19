package data.Util

import android.util.Log
import data.Models.Book
import data.Models.Ratings
import data.Models.SearchByName
import data.Models.SearchBySubject
import retrofit2.Response
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


    suspend fun searchBySubjectState(subject:String): SearchBySubjectState {
        /*var bothResults = mutableListOf<Any>()*/
        val response1 = bookapi.searchBooksBySubject(subject)
/*        val ratingsList = mutableListOf<Float>()*/
        if (response1.isSuccessful){
            return response1.body()?.toSearchBySubjectState()?:SearchBySubjectState()
/*            val doc = response1.body()!!.works
            var counter = 0
            while (counter  < doc.size){
                val response2 = bookapi.getRatings(doc[counter].key.substring(7))
                if(response2.isSuccessful){
                    Log.d("repository",response2.body()!!.summary.average.toString())
                    ratingsList.add(response2.body()!!.summary.average)
                    counter+=1
                }
            }
            bothResults.add(ratingsList)
        }*/}
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
            description = this.description
        )
    }
}