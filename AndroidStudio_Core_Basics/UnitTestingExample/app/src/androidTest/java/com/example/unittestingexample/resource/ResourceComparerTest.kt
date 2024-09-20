package com.example.unittestingexample.resource

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.unittestingexample.R
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ResourceComparerTest{
    private val resourceComparer = ResourceComparer()

    @Test
    fun stringResourceSameAsGivenString_returnTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name,"UnitTestingYT")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name,"Hello")
        assertThat(result).isFalse()
    }

}