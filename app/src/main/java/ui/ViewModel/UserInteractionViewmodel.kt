package ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserInteractionViewmodel : ViewModel(){
    // Firebase authentication instance
    private val auth: FirebaseAuth = Firebase.auth
    // Firebase Firestore instance
    private val firestore = Firebase.firestore

    //the book being searched by the user
    private var  _searchValue = MutableStateFlow<String>("")
    var searchValue: StateFlow<String> = _searchValue.asStateFlow()
    //bool on whether the user has started searching or not
    private var  _hasSearched = MutableStateFlow(false)
    var hasSearched: StateFlow<Boolean> = _hasSearched.asStateFlow()

    private var  _currentFriendsButton = MutableStateFlow(1)
    var currentFriendsButton: StateFlow<Int> = _currentFriendsButton.asStateFlow()

    private var _userNamesList = mutableListOf<String>()

    private var  _matchingUserNamesList = MutableStateFlow(listOf<String>())
    var matchingUserNamesList: StateFlow<List<String>> = _matchingUserNamesList.asStateFlow()

    private var  _friendsList = MutableStateFlow(listOf<String>())
    var friendsList: StateFlow<List<String>> = _friendsList.asStateFlow()

    private var  _friendsListState = MutableStateFlow(listOf<String>())
    var friendsListState: StateFlow<List<String>> = _friendsListState.asStateFlow()

    private var  _currentSelectedAccount = MutableStateFlow("")
    var currentSelectedAccount: StateFlow<String> = _currentSelectedAccount.asStateFlow()
    fun buttonColor(button:Int): Long {
        return if (button!=currentFriendsButton.value){
            0xFFFFFFFF
        }else{
            0xDBDBDBD0
        }
    }
    fun currentButton(button: Int){
        _currentFriendsButton.value = button
    }

    fun currentSelectedUser(username: String){
        _currentSelectedAccount.value = username
    }

    fun getUsernames() {
        firestore.collection("Users")
            .get()
            .addOnSuccessListener {
                for (doc in it.documents){
                    _userNamesList.add(doc.getString("username").toString())
                }
            }

    }

    fun getFriends() {
        firestore.collection("Users")
            .whereEqualTo("email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener {
                val doc = it.documents[0].data!!.get("friends") as Map<*, *>
                val tempList = mutableListOf<String>()
                for(friend in doc.get("addedFriends") as List<*>){
                    tempList.add(friend.toString())
                }
                _friendsList.value = tempList
            }

    }

    fun getUserInfo(username: String,name:(String)->Unit,friends:(String)->Unit) {
        firestore.collection("Users")
            .whereEqualTo("username",username)
            .get()
            .addOnSuccessListener {
                val doc = it.documents[0]
                val userName = doc.getString("fullname")
                val userFriends = doc.data!!.get("friends") as Map<*,*>
                friends((userFriends.get("addedFriends") as List<*>).size.toString())
                name(userName.toString())
            }
    }

/*    fun addFriend(username: String){
        firestore.collection("Users")
            .whereEqualTo("username,",username)
            .get()
            .addOnSuccessListener {
                val doc = it.documents[0].id.up
                val friendRequests = doc.get("friendRequests") as MutableList<*>

            }


    }*/

    fun matchingUsernames(username :String){
        viewModelScope.launch {
            var temlist = mutableListOf<String>()
            if (!username.isNullOrEmpty()){
                for (name in _userNamesList){
                    if (name.contains(username)){
                        temlist.add(name)
                    }
                }
            }
            _matchingUserNamesList.value = temlist
        }
    }

    /**
     * Updates the search value with the value of the latest search value given by the user.
     *
     * @param newSearchValue The new search value to be set.
     */
    fun updateSearchValue(newSearchValue:String){
        _searchValue.value = newSearchValue
    }

    /**
     * Sets the hasSearched state flow to true when the user searches
     */
    fun hasSearched(){
        _hasSearched.value = true
    }
    /**
     * Resets the hasSearched state flow variables when no search operation has been performed.
     */
    fun hasNotSearched(){
        _searchValue.value = ""

        _hasSearched.value = false
    }

    fun clear(){
        _matchingUserNamesList.value = emptyList()
    }
}