package com.example.networking

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query



const val Base_URL = "https://newsapi.org/"
const val API_KEY = "251ef143cc8349bd9b0ade4dfa284063"
interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    // linking method with the end point

    //send country and page number as a query string parameter
    // if we pass any country , it goes as query string parameter
    fun getHeadlines(@Query("country")country : String, @Query("page")page : Int) :
            Call<News> /* we get the news object here*/


    // now we will use handel the json return by the help of converter
}

// now create one retrofit object in singleton , whenever we need to call it, we call and get the data

object NewsService {

    val newsInstance : NewsInterface
    init {
        // retrofit object created
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // giving the implementation of instance
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}