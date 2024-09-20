package com.example.calling_two_apis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://your-api-base-url.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://meme-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val firstApiCall = apiService.firstApiCall()
        firstApiCall.enqueue(object : Callback<FirstApiResponse>{
            override fun onResponse(
                call: Call<FirstApiResponse>,
                response: Response<FirstApiResponse>
            ) {
                if (response.isSuccessful){
                    val firstApiResult = response.body()
                    // using the result to another
                    if (firstApiResult != null){
                        val secondApiCall = apiService.secondApiCall(firstApiResult.author)
                        secondApiCall.enqueue(object : Callback<SecondApiResponse>{
                            override fun onResponse(
                                call: Call<SecondApiResponse>,
                                response: Response<SecondApiResponse>
                            ) {
                                if (response.isSuccessful){
                                    val secondApiResult = response.body()
                                    // handling the response
                                    if( secondApiResult != null) {
                                        // Process the second API response
                                    }
                                } else {
                                    val errorMessage = response.errorBody()?.string()
                                    Log.e("API", "Second API call failed: $errorMessage")
                                    // Handle API error
                                }
                            }
                            override fun onFailure(call: Call<SecondApiResponse>, t: Throwable) {
                                Log.e("API", "Second API call failed: ${t.message}")
                                // Handle network or API error
                            }

                        })
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e("API", "First API call failed: $errorMessage")
                }
            }
            override fun onFailure(call: Call<FirstApiResponse>, t: Throwable) {
                Log.e("API", "First API call failed: ${t.message}")
            }

        })
    }
}