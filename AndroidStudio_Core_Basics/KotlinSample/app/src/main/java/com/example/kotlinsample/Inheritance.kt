package com.example.kotlinsample

open class InheritanceP { // Parent class
    open var name = "John"
    open fun Add(a:Int, b:Int):Int{
        return a+b
    }
}
class ClassB : InheritanceP(){
    override var name:String= "Abhay"
    override fun Add(a: Int, b: Int): Int {
        val sum= super.Add(a, b)
        val sqr=sum*sum
        return sqr
    }

}

fun main(){
    var cb = ClassB()
    println("The Name is : "+ cb.name)
    println("the sum is : "+cb.Add(5,6))
}
