package com.example.buylist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface ItemDAO {

    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)
}
