package data.Util

import com.google.gson.GsonBuilder
import data.Models.Author
import data.Models.Book
import data.Models.Ratings
import data.Models.SearchByName
import data.Models.SearchBySubject
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi{
    @GET("works/{id}.json/")
    suspend fun getBooksApi(@Path(value ="id")id:String): Response<Book>

    @GET("/subjects/{subject}.json?limit=100")
    suspend fun searchBooksbySubjectApi(@Path(value ="subject")id:String): Response<SearchBySubject>

    @GET("https://openlibrary.org/works/{id}/ratings.json")
    suspend fun getRatings(@Path(value = "id")id:String):Response<Ratings>

    @GET("/authors/{id}.json")
    suspend fun getAuthor(@Path(value = "id")id:String):Response<Author>

    @GET("search.json")
    suspend fun searchBooksbyNameApi(@Query("title")search:String, @Query("mode")mode:String="everything", @Query("limit")limit:String="20"):Response<SearchByName>
}




class BookApiService() {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    suspend fun getBook(id :String) : Response<Book>{
        return retrofit.create(RetrofitApi::class.java).getBooksApi(id)
    }
    suspend fun getRatings(id :String) : Response<Ratings>{
        return retrofit.create(RetrofitApi::class.java).getRatings(id)
    }

    suspend fun getAuthor(id :String) : Response<Author>{
        return retrofit.create(RetrofitApi::class.java).getAuthor(id)
    }

    suspend fun searchBooksBySubject(subject:String):Response<SearchBySubject>{
        return retrofit.create(RetrofitApi::class.java).searchBooksbySubjectApi(subject)
    }

    suspend fun searchBooksByName(search :String) : Response<SearchByName> {
        return retrofit.create(RetrofitApi::class.java).searchBooksbyNameApi(search)
    }
}

