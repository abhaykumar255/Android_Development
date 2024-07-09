package com.example.mywishlistapp.graph

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mywishlistapp.data.WishDataBase
import com.example.mywishlistapp.repository.WishRepository

object Graph {
    private lateinit var dataBase: WishDataBase

    val wishRepository by lazy {
        WishRepository(wishDao = dataBase.wishDao())
    }

    fun provide(context: Context) {
        dataBase = Room.databaseBuilder(context, WishDataBase::class.java, "wishList.db")
            .build()
    }
}