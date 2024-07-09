package com.example.mywishlistapp

import android.app.Application
import android.util.Log
import com.example.mywishlistapp.graph.Graph
import com.example.mywishlistapp.utils.NetworkUtils

class  WishApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
        val networkUtils  = NetworkUtils()
        Log.d("NetWorkUtil","Network is ${networkUtils.isNetworkAvailable(this.applicationContext)}")
    }
}