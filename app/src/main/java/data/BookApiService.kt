package data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RetrofitApi{
    @GET("works/{id}.json/")
    suspend fun getBooksApi(@Path(value ="id")id:String):Book
}




class BookApiService() {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getBook(id :String) : Book{
        return retrofit.create(RetrofitApi::class.java).getBooksApi(id)
    }
}