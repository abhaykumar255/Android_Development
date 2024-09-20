package com.example.unittestingexample.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperTest {
    private lateinit var helper: Helper

    @Before
    fun setup(){
        helper = Helper()
        //println("Before every test cases")
    }
    @After
    fun tearDown(){
        println("After Every test cases")
    }
    @Test
    fun isPallindrome() {
        // Arrange
        // Act
        val result = helper.isPallindrome("hello")
        // Assert
        assertEquals(false,result)
    }
    @Test
    fun isPallindrome_check_true() {
        // Arrange

        // Act
        val result = helper.isPallindrome("level")
        // Assert
        assertEquals(true,result)
    }
}