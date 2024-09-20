package com.example.kotlinsample

class ClassA{
    var anotherInt = 50
    fun add(a:Int, b:Int): Int{
        return a+b
    }
    companion object{
        var someInt= 100
        fun product(a:Int, b:Int):Int{
            return a*b
        }
    }
}
fun main(){
    // to get the anotheInt variable , we have to create the class object
    val cc = ClassA()
    println("first no is "+ cc.anotherInt)
    println("Sum is "+ cc.add(5,6))

    // to access the elements is companion object , we can call it directly by clss name without creating instance becoz memory is already allocated
    println("Product is "+ ClassA.product(5,6))
    println(" Second number is "+ ClassA.someInt)

}