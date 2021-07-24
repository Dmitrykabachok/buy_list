package com.example.buylist

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var editText: EditText
    private lateinit var countText: EditText
    private lateinit var loadButton: Button
    private lateinit var db: AppDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter
    lateinit var adapterList: ArrayList<Model>
    private var list: ArrayList<Item> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_name)
        countText = findViewById(R.id.edit_count)
        saveButton = findViewById(R.id.Save_btn)
        loadButton = findViewById(R.id.Load_btn)
        recyclerView = findViewById(R.id.recycler)
        adapterList = Model.convert(list)
        adapter = Adapter { item-> deleteItem(item as Item) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        db = (applicationContext as DatabaseCreateProv).getDatabase()


        loadButton.setOnClickListener {
            Log.d("DATABASE_ACTIVE", "LOAD")
            CoroutineScope(Dispatchers.IO).launch {
                val dblist = db.itemDao().getAll()
                list.clear()
                dblist.forEach {
                    list.add(it)
                }
            }
            adapter.setData(list)
        }

        saveButton.setOnClickListener {
            Log.d("DATABASE_ACTIVE", "START")

            val name: String = editText.text.toString()
            val count: Int = countText.text.toString().toInt()
            val item = Item(name = name, count = count)
            runBlocking { launch { saveInfo(item) } }
        }
    }

    private suspend fun saveInfo(item: Item) {
        db.itemDao().insert(item)
        Log.d("DATABASE_ACTIVE", "SAVED")
    }

    private fun deleteItem(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("DATABASE_ACTIVE", "DELETED")
            db.itemDao().delete(item)
            val dblist = db.itemDao().getAll()
            list.clear()
            dblist.forEach {
                list.add(it)
            }
        }
        adapter.setData(list)
        }


}
