package com.example.lifecycle_basic

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Observer : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.d("Observer","On Create Called ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onBack(){
        Log.d("Observer","On Back Pressed Called ")
    }
}