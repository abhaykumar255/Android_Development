package com.example.testinginandroid.utils

import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters


@RunWith(value = Parameterized::class)
class ParameterizedExapmleTest( val input : String , val expectedValue : Boolean) {

    @Test
    fun test(){
        val helper = Helper()
        val result = helper.isPallindrome(input)
        assertEquals(expectedValue,result)
    }

    companion object{

        @JvmStatic
        @Parameters(name = "{index} : {0} is pallindrome - {1}")
        fun data() : List<Array<Any>> {
            return listOf(
                arrayOf("hello",false),
                arrayOf("level",true),
                arrayOf("",true),
                arrayOf("a",true),
                arrayOf("12121",true),
                arrayOf("abhay",false)
            )
        }
    }
}