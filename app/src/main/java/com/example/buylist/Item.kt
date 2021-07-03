package com.example.buylist


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id : Int = 0,
    @ColumnInfo(name="name")val name : String = "",
    @ColumnInfo(name="count") val count : Int = 0
)

