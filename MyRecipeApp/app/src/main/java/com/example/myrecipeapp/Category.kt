package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// based on mealDb
@Parcelize
data class Category(
    val idCategory : String,
    val strCategory : String,
    val strCategoryThumb : String,
    val strCategoryDescription : String
) : Parcelable

data class CategoriesResponse(
    val categories : List<Category>
)

// for post sample
data class RandomResponse(
    val message : List<Category>
)

data class RandomRequest(
    val count : Int
)