package data.Util

import data.Models.Book
import data.Models.Search
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi{
    @GET("works/{id}.json/")
    suspend fun getBooksApi(@Path(value ="id")id:String): Response<Book>

    @GET("search.json")
    suspend fun searchBooksApi(@Query("q")search:String,@Query("mode")mode:String="everything",@Query("limit")limit:String="10"):Response<Search>
}




class BookApiService() {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getBook(id :String) : Response<Book>{
        return retrofit.create(RetrofitApi::class.java).getBooksApi(id)
    }

    suspend fun searchBook(search :String) : Response<Search> {
        return retrofit.create(RetrofitApi::class.java).searchBooksApi(search)
    }
}

