package com.example.kotlinsample

//var fruits: List<String> = listOf("Banana", "Apple", "Mango")
// for changing element in list
var fruits: MutableList<String> = mutableListOf("Banana", "Apple", "Mango")
fun main(){
    println("Size of list is : "+fruits.size)

    fruits.forEach{

        // i->println(i)
        fruit->
        println("this is $fruit")
    }
    fruits.add("Orange")
    println("Size is ${fruits.size} ")

    fruits.forEach{

        // i->println(i)
            fruit->
        println(fruit)
    }

}