package com.example.restapidemo.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// used to create retrofit client and to handle APIs

const val BASEURL = "https://jsonplaceholder.typicode.com/"

class ApiClient {
    companion object{
        private var retrofit : Retrofit? = null
        fun getApiClient():Retrofit{
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(1000,TimeUnit.SECONDS)
                .connectTimeout(100,TimeUnit.SECONDS)
                .build()

            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!
        }
    }
}