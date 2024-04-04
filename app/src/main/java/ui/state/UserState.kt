package ui.state

import data.Models.Friends


data class UserState(
    val userId: String = "",
    val email: String = "",
    val username:String = "",
    val fullname :String = "",
    val savedBooks:MutableList<String> = mutableListOf(),
    val friends: Friends = Friends(mutableListOf(), mutableListOf())
)
