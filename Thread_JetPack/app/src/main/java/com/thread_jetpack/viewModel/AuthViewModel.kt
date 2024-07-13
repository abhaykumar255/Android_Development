package com.thread_jetpack.viewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.storage
import com.thread_jetpack.model.UserModel
import com.thread_jetpack.utils.SharedfPref
import java.util.UUID

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    private val useRef = db.getReference("users")

    private val storageRef = Firebase.storage.reference
    private val imageRef = storageRef.child("users/${UUID.randomUUID()}.jpg")

    // checking the user is login or not =>  checking the user email and uid
    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> = _firebaseUser

    // for getting the error while login or signup
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        // will get the login user here
        _firebaseUser.value = auth.currentUser
    }

    fun signIn(email: String, password: String, context: Context) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _firebaseUser.postValue(auth.currentUser)
                    getData(auth.currentUser?.uid, context)
                } else
                    _error.postValue(it.exception!!.message)
            }
    }

    private fun getData(uid: String?, context: Context) {
        useRef.child(uid!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(UserModel::class.java)
                SharedfPref.storeData(
                    userData!!.email,
                    userData.name,
                    userData.bio,
                    userData.userName,
                    userData.image,
                    context
                )
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun registerUser(
        email: String,
        password: String,
        name: String,
        bio: String,
        userName: String,
        imageUri: Uri?,
        context: Context
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _firebaseUser.postValue(auth.currentUser)
                    // storing the image in store and other data in realtime database
                    saveImage(
                        email,
                        password,
                        name,
                        bio,
                        userName,
                        imageUri,
                        auth.currentUser?.uid,
                        context
                    )
                } else {
                    _error.postValue(it.exception!!.message)
                }
            }
    }

    private fun saveImage(
        email: String,
        password: String,
        name: String,
        bio: String,
        userName: String,
        imageUri: Uri?,
        uid: String?,
        context: Context
    ) {
        val uploadTask = imageRef.putFile(imageUri!!)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(email, password, name, bio, userName, it.toString(), uid, context)
            }
        }
    }

    private fun saveData(
        email: String,
        password: String,
        name: String,
        bio: String,
        userName: String,
        image: String,
        uid: String?,
        context: Context
    ) {
        val userData = UserModel(email, password, name, bio, userName, image, uid!!)

        useRef.child(uid).setValue(userData)
            .addOnSuccessListener {
                // storing data is shared preference
                SharedfPref.storeData(email, name, bio, userName, image, context)
            }.addOnFailureListener {

            }
    }

    fun signOut() {
        auth.signOut()
        _firebaseUser.postValue(null)
    }
}