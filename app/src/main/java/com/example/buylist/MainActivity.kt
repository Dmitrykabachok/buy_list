package com.example.buylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(){
    lateinit var saveButton : Button
    lateinit var editText : EditText
    lateinit var textView : TextView
    lateinit var countText : EditText
    lateinit var db : AppDatabase
    var id : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_name)
        countText = findViewById(R.id.edit_count)
        textView = findViewById(R.id.list)
        saveButton=findViewById(R.id.Save_btn)
        db = (applicationContext as DatabaseCreateProv).getDatabase()



        editText.setText("a")
        countText.setText("1")
        var name : String = editText.text.toString()
        var count : Int = countText.text.toString().toInt()
        var item : Item = Item(id,name,count)


        saveButton.setOnClickListener {
            Log.d("DATABASE_ACTIVE", "START")
            runBlocking { launch {  db.itemDao().getAll()} }


        }
    }

     suspend fun getInfo(): String {
         var text : String  = db.itemDao().getAll().toString()
         Log.d("DATABASE_ACTIVE", "LOADED")
         return text
    }

     suspend fun saveInfo(item : Item): Int {

         db.itemDao().insertAll(item)
         id++
         Log.d("DATABASE_ACTIVE", "SAVED")
         return 1
    }


}