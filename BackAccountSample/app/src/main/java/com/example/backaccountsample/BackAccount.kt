package com.example.backaccountsample

class BackAccount(var accountHolder: String, var balance: Double) {

    private val transactionHistory = mutableListOf<String>()
    fun deposit(amount : Double){
        balance+= amount
        transactionHistory.add("$accountHolder deposited $$amount")
    }
    fun withdraw(amount: Double){
        if (amount <= balance){
            // we can withdraw
            balance-= amount
            transactionHistory.add("$accountHolder withdraw $$amount")
        }else{
            // we cannot withdraw
            println("You don't have funda to withdraw")
        }

    }
    fun displayTransactionHistory(){
        println("Transaction history for $accountHolder")
        for (item in transactionHistory)
            println(item)
    }
}