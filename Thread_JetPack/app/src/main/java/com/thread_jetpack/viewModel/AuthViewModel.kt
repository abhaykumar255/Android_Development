package com.thread_jetpack.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel : ViewModel() {
    val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    val useRef = db.getReference("users")

    // checking the user is login or not =>  checking the user email and uid
    private val _firebaseUser = MutableLiveData<FirebaseUser>()
    val firebaseUser: LiveData<FirebaseUser> = _firebaseUser

    // for getting the error while login or signup
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        // will get the login user here
        _firebaseUser.value = auth.currentUser
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful)
                    _firebaseUser.postValue(auth.currentUser)
                else
                    _error.postValue("Something went wrong")
            }
    }

    fun registerUser(email: String, password: String, name: String, bio: String, userName: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful)
                    _firebaseUser.postValue(auth.currentUser)
                else
                    _error.postValue("Something went wrong")
            }
    }
}