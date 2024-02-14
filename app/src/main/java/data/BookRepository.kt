package data

class BookRepository(private val bookapi : BookApiService) {


    suspend fun searchBooksState(search:String): Search? {
        val response = bookapi.searchBook(search)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}