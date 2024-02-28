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
import data.Models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel:ViewModel(){
    private val auth:FirebaseAuth= Firebase.auth
    private val firestore = Firebase.firestore

    private var  _wrongInfo = MutableStateFlow<Boolean>(false)
    var wrongInfo: StateFlow<Boolean> = _wrongInfo.asStateFlow()


    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var repeatPassword by mutableStateOf("")
        private set


    fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            _wrongInfo.value = true
                            Log.d("ERROR EN FIREBASE","Usuario y/o contrasena incorrectos")
                        }
                    }
            } catch (e: Exception){
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }


    fun createUser(onSuccess: () -> Unit){
        viewModelScope.launch {
            if (password == repeatPassword){
                try {
                    // DCS - Utiliza el servicio de autenticación de Firebase para registrar al usuario
                    // por email y contraseña
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // DCS - Si se realiza con éxito, almacenamos el usuario en la colección "Users"
                                saveUser()
                                onSuccess()
                            } else {
                                Log.d("ERROR EN FIREBASE","Error al crear usuario")
                                _wrongInfo.value = true
                            }
                        }
                } catch (e: Exception){
                    Log.d("ERROR CREAR USUARIO", "ERROR: ${e.localizedMessage}")
                }
            }
            else{
                _wrongInfo.value = true
            }
        }
    }

    private fun saveUser(){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(
                userId = id.toString(),
                email = email.toString(),
            )
            // DCS - Añade el usuario a la colección "Users" en la base de datos Firestore
            firestore.collection("Users")
                .add(user)
                .addOnSuccessListener { Log.d("GUARDAR OK", "Se guardó el usuario correctamente en Firestore") }
                .addOnFailureListener { Log.d("ERROR AL GUARDAR", "ERROR al guardar en Firestore") }
        }
    }



    fun changeError(){
        _wrongInfo.value = false
    }

    fun changeEmail(email: String) {
        this.email = email
    }

    fun changePassword(password: String) {
        this.password = password
    }

    fun changeRepeatPassword(password: String) {
        this.repeatPassword = password
    }


}