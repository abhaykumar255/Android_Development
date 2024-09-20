package com.example.testinginandroid.utils

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class HelperTest {

    @Before
    fun setUp() {
        println("Before")
    }

    @After
    fun tearDown() {
        println("After")
    }

    @Test
    fun isPallindrome() {
        val helper = Helper()
        var result = helper.isPallindrome("hello")
        assertEquals(false,result)
    }
}