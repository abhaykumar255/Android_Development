package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)

object DummyList {
    val wishList = listOf(
        Wish(title = "Google Watch 1", description = "An Android watch 1 flexing it for purposely purposely"),
        Wish(title = "Google Watch 2", description = "An Android watch 2 flexing it for purposely"),
        Wish(title = "Google Watch 3", description = "An Android watch 3 flexing it for purposely purposely"),
        Wish(title = "Google Watch 4", description = "An Android watch 4 flexing it for purposely"),
        Wish(title = "Google Watch 5", description = "An Android watch 5 flexing it for purposely purposely")
    )
}