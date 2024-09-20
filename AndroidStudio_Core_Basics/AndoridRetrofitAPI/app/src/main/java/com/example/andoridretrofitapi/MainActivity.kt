package com.example.andoridretrofitapi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.*
import com.example.andoridretrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // interface -> converter -> object

    //https://meme-api.com/gimme

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        binding.btnNewMeme.setOnClickListener{
            getData()
        }
    }

    private fun getData() {

        var progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetch")
        progressDialog.show()
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responceDataClass?>{
            override fun onResponse(
                call: Call<responceDataClass?>,
                response: Response<responceDataClass?>,
            ) {
                // binding
                binding.memeTitle.text = response.body()?.title
                binding.memeAuthor.text = response.body()?.author
                // to load image and binding
                Glide.with(this@MainActivity).load(response.body()?.url).into(binding.memeImage)

                progressDialog.dismiss()
            }
            override fun onFailure(call: Call<responceDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.localizedMessage,Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

        })
    }
}