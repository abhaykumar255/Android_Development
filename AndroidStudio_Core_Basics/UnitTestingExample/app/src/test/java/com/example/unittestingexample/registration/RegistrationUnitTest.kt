package com.example.unittestingexample.registration

import org.junit.Assert.*

import org.junit.Test

class RegistrationUnitTest {

    @Test
    fun emptyUsernameCheck() {
        val result = RegistrationUnit.validateRegistrationInput(
            username = "",
            password = "123",
            confirmPassword = "123"
        )
        assertEquals(false,result)
    }
    @Test
    fun validUsernameCorrectPassword() {
        val result = RegistrationUnit.validateRegistrationInput(
            username = "abhay1",
            password = "123",
            confirmPassword = "123"
        )
        assertEquals(true,result)
    }
    @Test
    fun usernameExixtsreturnfalse() {
        val result = RegistrationUnit.validateRegistrationInput(
            username = "rajput",
            password = "123",
            confirmPassword = "123"
        )
        assertEquals(false,result)
    }
}