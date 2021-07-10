package com.example.buylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var countText: EditText
    private lateinit var loadButton: Button
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_name)
        countText = findViewById(R.id.edit_count)
        textView = findViewById(R.id.list)
        saveButton = findViewById(R.id.Save_btn)
        loadButton = findViewById(R.id.Load_btn)
        db = (applicationContext as DatabaseCreateProv).getDatabase()

        loadButton.setOnClickListener{
            Log.d("DATABASE_ACTIVE","LOAD")
            CoroutineScope(Dispatchers.IO).launch {
                val list = db.itemDao().getAll()
                withContext(Dispatchers.Main){
                    textView.text = list.toString()
                }
            }
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
}
