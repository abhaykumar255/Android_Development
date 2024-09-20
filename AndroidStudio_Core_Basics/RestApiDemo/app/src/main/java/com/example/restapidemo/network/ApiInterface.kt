package com.example.restapidemo.network

import com.example.restapidemo.home.data.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("post")
    fun fetchAllPosts():Call<List<PostModel>>
}