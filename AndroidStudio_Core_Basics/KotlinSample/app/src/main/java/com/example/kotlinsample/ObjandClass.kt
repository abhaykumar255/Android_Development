package com.example.kotlinsample

fun main(){
    //val kb = ObjandClass()
    println("Integer is : "+ ObjandClass(50,).someInt)
    println("Sum is " + ObjandClass(60,60).add(5,6))
}
class ObjandClass {
    var someInt = 10
//    init {
//        println("We are getting : " +a)
//    }
    // if we have to use secondary constructor , then we have to remove the primary constructor i.e. constructor(val a:Int)
    // custom constructor
    constructor(a:Int , b:Int=100){
        val sum= a+b
        println("Sum of two " +sum)
    }
    constructor(a:Int,b:Int,c:Int=100){
        val sum = a+b+c
        println("Addition of three : " + sum )
    }

    fun add(a:Int, b:Int) : Int {
        return a+b
    }
}


