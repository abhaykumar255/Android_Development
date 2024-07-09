package com.example.backaccountsample

fun main(){
    val abhayKumar = BackAccount("Abhay Kumar",5000.00)
    abhayKumar.deposit(5000.00)
    abhayKumar.deposit(2000.00)
    abhayKumar.withdraw(3000.00)
    abhayKumar.deposit(3294.00)
    abhayKumar.displayTransactionHistory()
    println("Remaining balance is $${abhayKumar.balance}")
}