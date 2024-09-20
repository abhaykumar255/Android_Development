package com.example.andoridretrofitapi

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    // to hit the endpoint

    @GET("gimme")
    fun getData():Call<responceDataClass>

    fun firstApicall()

}