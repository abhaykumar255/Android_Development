package com.example.calling_two_apis

import com.google.gson.annotations.SerializedName

data class FirstApiResponse(
    var author : String,
    var nsfw : Boolean,
    var postLink : String,
    var preview : List<String>,
    var spoiler : Boolean,
    var subreddit : String,
    var title : String,
    var ups : Int,
    val url : String
)
