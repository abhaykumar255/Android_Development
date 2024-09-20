package com.example.calling_two_apis

data class SecondApiResponse(

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
