package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // three steps to do this
    /*
    1. Interface create - News Service
    2. Converters
    3. Retrofit Object
      */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }
    private fun getNews(){
        val news = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null)
                    Log.d("Abhay", news.toString())
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Abhay","Error in fetching news")
            }

        })
    }
}