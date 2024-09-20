package com.example.testinginandroid

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type

class QuoteManager() {

    var quoteList = emptyArray<Quote>()
    var currentQuoteIndex = 0
    lateinit var sample : String

    fun populateQuoteAssets(context: Context, filename : String){
        val inputStream = context.assets.open(filename)
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json,Array<Quote>::class.java)
    }

    fun populateQuotes(quotes : Array<Quote>){
        quoteList = quotes
    }

    fun getCurrentQuote() : Quote {
        return quoteList[currentQuoteIndex]
    }

    fun getNextQuote() : Quote {
        if (currentQuoteIndex == quoteList.size -1 ) return  quoteList[currentQuoteIndex]
        return quoteList[++currentQuoteIndex]
    }

    fun getPrevious() : Quote {
        if (currentQuoteIndex == 0) return quoteList[currentQuoteIndex]
        return quoteList[--currentQuoteIndex]
    }

}