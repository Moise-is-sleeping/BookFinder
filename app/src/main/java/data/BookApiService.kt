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
import retrofit2.http.Url

interface RetrofitApi{
    @GET
    suspend fun getBooksApi(@Url url :String):Book
}




class BookApiService {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/works/OL9177076M.json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getBook() : Book{
        return retrofit.create(RetrofitApi::class.java).getBooksApi(retrofit.baseUrl().toString())
    }
}