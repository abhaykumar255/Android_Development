package com.example.kotlinsample

//public class MyClass : ClassA() {
class MyClass{

    // companion object-
    companion object{
        // this is the main function act like in java "public static void main"
        @JvmStatic
        // passing the string array as an argument
        fun main(args: Array<String>){
            println("Abhay !")
            val i:Int=5
            println(i)
            println("Number is $i")
            println("Outside " + i)
            // we have to use {} if we r passing bigger value in function
            println("Sum is ${add(5,5,11)}")
            println("Sum for 2 paraone ${add(5,5)}")

            // looping and conditioning
            var n= 11
            var msg=""
//            if(n>100){
//                println("number is greater")
//            }
//            else{
//                println("number is smaller")
//            }
            msg = if(n>100) "Number is greater" else "Number is smaller"
            println(msg)

            when(n){
                1 -> {

                }
                100 -> {

                }
            }
            when{
                n>100 ->{

                }
                n<100 ->{

                }
            }

            // for loop
            var num=0
            // for (i in 0..10){}
            // 10 is included in it, if we want not to include 10 then we can use also untill to not include the 10
            //fun(i in 0 untill 10)
            // for decrement loop -> for (i in 10 downTo 0)
            // step is used to howmuch we want to leave
            for(i in 1..10 step 2){
                println("Number is $i")
            }

            // for array
            var Array1 = arrayOf(1,10,4,6,15)
            var Array2 = arrayOf<Int>(1,10,4,6,15)
            val Array3 = arrayOf<String>("Ajay","Prakesh","Michel","John","Sumit")
            var Array4= arrayOf(1,10,4, "Ajay","Prakesh")
            var Array5: IntArray = intArrayOf(5,10,20,12,15)

            Array1.set(0,5)
            Array1[2]=25
            var arraysize=Array1.size
            println("Array Size is $arraysize")
            Array1.get(0)

            var arrNo=ArrayList<Int>()
            arrNo.add(10)
            arrNo.add(20)
            arrNo.add(30)
            arrNo.add(40)
            arrNo.add(50)
            arrNo.add(60)
            arrNo.add(70)
            arrNo.add(80)
            arrNo.add(90)
            arrNo.add(100)

            for(i in arrNo){
                println("Array value is $i")
            }

            // Pairing
            var (a,b) = Pair("Abhay", "Pratap")
            println("$a $b")

            var name= Pair("Abhay", "Pratap")
            println("${name.first} ${name.second}")

            var (ac,bb,cc) = Triple("Abhay","Pratap","Singh")
            var name1 = Triple("Abhay","Pratap","Singh")
            println("$name1")




        }
        // method
        // function returning the integer type value as we have used Int
        // or we also used Any, if we don't know the returning type
        fun add(a: Int, b: Int) : Any{
            return a+b
        }
        fun add(a: Int, b: Int, c:Int) : Any{
            return a+b+c
        }

    }

}