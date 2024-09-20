package com.example.restapidemo.home.data

// data model class
data class PostModel(
    var userId : Int?=0,
    var id : Int? = 0,
    var title : String = "",
    var body : String? = ""
)
