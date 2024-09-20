package com.example.recyclerdbhandle

import java.util.*

data class ItemViewModel(
    val id : Int = getAutoID(),
    val name : String,
    val age : Int) {
    companion object{
        fun getAutoID() : Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}