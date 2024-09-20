package com.example.androidvolley

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.androidvolley.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var url : String = "https://meme-api.com/gimme"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData();
        binding.btnNewMeme.setOnClickListener{
            getData()
        }
    }

    private fun getData() {

        var progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetch")
        progressDialog.show()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this@MainActivity)

        // Request a string response from the provided URL.
        // passing method and url and then responce
        val stringRequest = StringRequest(
            Request.Method.GET,url,
            { response ->
                Log.e("Response","getMemeData" + response.toString())

                var responseObject = JSONObject(response)

                binding.memeTitle.text = responseObject.getString("title")
                binding.memeAuthor.text = responseObject.getString("author")
                Glide.with(this@MainActivity).load(responseObject.get("url")).into(binding.memeImage)
                progressDialog.dismiss()
            },
            { error ->
                Toast.makeText(this@MainActivity,"${error.localizedMessage}",Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}