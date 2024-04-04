package data.Models


/**
 * Data class with the information about each user
 */
data class User(
    val userId: String,
    val email: String,
    val username:String,
    val fullname :String,
    val savedBooks:MutableList<String>,
    val friends:Friends
)

data class Friends(
    val friendRequests:MutableList<String>,
    val addedFriends:MutableList<String>
)