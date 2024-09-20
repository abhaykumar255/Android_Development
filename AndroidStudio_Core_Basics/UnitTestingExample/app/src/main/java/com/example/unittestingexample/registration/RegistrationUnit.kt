package com.example.unittestingexample.registration

object RegistrationUnit {

    private val exixtinguser = listOf("abhay","rajput")

    fun validateRegistrationInput(
        username: String,
        password : String,
        confirmPassword : String
    ): Boolean {
        if(username.isEmpty() || password.isEmpty())
            return false
        if (username in exixtinguser)
            return false
        if(password !=confirmPassword)
            return false
        if (password.count{it.isDigit()} < 2)
            return false
        return true
    }
}