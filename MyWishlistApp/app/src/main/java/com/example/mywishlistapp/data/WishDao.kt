package com.example.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addWish(wishEntity: Wish)

    // Loads all wishes from table
    @Query("Select * From `wish-table`")
    abstract fun getAllWishes():Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Query("Select * From `wish-table` where id=:id")
    abstract fun getWishById(id : Long ):Flow<Wish>
}