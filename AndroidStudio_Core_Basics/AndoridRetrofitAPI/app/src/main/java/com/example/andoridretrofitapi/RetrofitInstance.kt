package com.example.andoridretrofitapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // retrofit object create
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://meme-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // connecting with the api interface
    var apiInterface = retrofit.create(APIInterface::class.java)
}