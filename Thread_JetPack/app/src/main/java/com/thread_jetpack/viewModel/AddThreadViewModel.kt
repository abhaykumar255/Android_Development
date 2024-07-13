package com.thread_jetpack.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage
import com.thread_jetpack.model.ThreadModel
import java.util.UUID

class AddThreadViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    private val useRef = db.getReference("threads")

    private val storageRef = Firebase.storage.reference
    private val imageRef = storageRef.child("threads/${UUID.randomUUID()}.jpg")

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted

    fun saveImage(
        thread: String,
        userId: String,
        imageUri: Uri?,
    ) {
        val uploadTask = imageRef.putFile(imageUri!!)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(thread, userId, it.toString())
            }
        }
    }

    fun saveData(
        thread: String,
        userId: String,
        image: String,
    ) {
        val threadData = ThreadModel(thread, userId, image, System.currentTimeMillis().toString())

        useRef.child(useRef.push().key!!).setValue(threadData)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }.addOnFailureListener {
                _isPosted.postValue(false)
            }
    }
}