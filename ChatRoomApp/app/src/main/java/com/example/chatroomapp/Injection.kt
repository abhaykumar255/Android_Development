package com.example.chatroomapp

import com.google.firebase.firestore.FirebaseFirestore

/*
We need to initialize the userRepository which is dependent
on both the FirebaseAuth and FirestoreDatabase. For the
auth instance we can call it directly within the class
without having to provide a global access to it because
this is the only class we would need it. For the Firestore
we will need to create an object class which can make it
accessible to other classes that will be dependent on it.
 */
object Injection {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun instance(): FirebaseFirestore {
        return instance
    }
}