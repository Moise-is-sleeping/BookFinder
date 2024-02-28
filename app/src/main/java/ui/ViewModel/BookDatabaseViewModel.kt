package ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class BookDatabaseViewModel(): ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore


    private var  _hasSaved = MutableStateFlow(false)
    var hasSaved: StateFlow<Boolean> = _hasSaved.asStateFlow()




    var bookIdList by mutableStateOf(mutableListOf(""))

    var bookId by mutableStateOf("")
        private set

    fun SaveBooks(onSuccess:() -> Unit) {
        val email = auth.currentUser?.email
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newBook = hashMapOf(
                    "BookId" to bookId,
                    "UserEmail" to email.toString(),
                )
                // DCS - Utilizar la instancia de Firestore para agregar una nota
                firestore.collection("SavedBooks")
                    .add(newBook)
                    .addOnSuccessListener {
                        onSuccess()
                        Log.d("GUARDAR OK", "Se guardó la nota correctamente en Firestore")
                    }
                    .addOnFailureListener {
                        Log.d("ERROR AL GUARDAR", "ERROR al guardar en Firestore ${it.message}")
                    }
            } catch (e: Exception){
                Log.d("ERROR GUARDAR NOTA","Error al guardar ${e.localizedMessage} ")
            }
        }
    }

    fun hasSaved(){
        _hasSaved.value = !_hasSaved.value
    }

    fun hasSavedDefaultValue(Id:String){
        if (Id in bookIdList){
            _hasSaved.value = true
        }else{
            _hasSaved.value = false
        }
        bookId = Id

    }

    fun check(): Boolean {
        return bookId !in bookIdList
    }

    fun addIdOrRemove(value:Boolean){
        if (value && bookId !in bookIdList){

            bookIdList.add(bookId)
        }else if (!value){
            bookIdList.remove(bookId)
        }
        Log.d(" _hasSaved",bookIdList.toString() )
    }

    fun changeBookId(bookID:String){
        bookId = bookID
    }



}