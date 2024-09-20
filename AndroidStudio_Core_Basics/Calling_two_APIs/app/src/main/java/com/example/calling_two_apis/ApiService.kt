package com.example.calling_two_apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("gimme")
    fun firstApiCall(): Call<FirstApiResponse>

    @GET("gimme")
    fun secondApiCall(@Query("data") data: String): Call<SecondApiResponse>


}