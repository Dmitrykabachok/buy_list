package com.example.buylist

import android.os.Build
import androidx.annotation.RequiresApi

class Model(val name: String, val count: Int) {
    companion object{

        @RequiresApi(Build.VERSION_CODES.N)
        fun convert(itemList: ArrayList<Item>): ArrayList<Model>{
            val list = ArrayList<Model>()
            itemList.forEach{
                list.add(Model(it.name, it.count))
            }
            return list
        }
    }
}