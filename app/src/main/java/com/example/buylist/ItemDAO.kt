package com.example.buylist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ItemDAO {

    @Query("SELECT * FROM ITEM")
     suspend fun getAll(): LiveData<List<Item>>

    @Insert
     suspend fun insertAll(vararg items : Item)

    @Delete
     suspend fun delete(item : Item)
}