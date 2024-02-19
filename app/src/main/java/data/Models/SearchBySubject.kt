package data.Models

data class SearchBySubject(
    var works : List<Works>,


)
data class Works(
    var key :String,
    var title:String,
    var authors:List<Authors>,
    var cover_id :Int,

)

data class Authors(
    var key: String,
    var name:String
)