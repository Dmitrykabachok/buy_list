package com.example.buylist

import android.graphics.ColorSpace
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById<TextView>(R.id.itemName)
    val count: TextView = itemView.findViewById<TextView>(R.id.itemCount)
    val deleteButton: Button = itemView.findViewById<Button>(R.id.deleteButton)

    fun bind(person: Item){
        name.text = person.name
        count.text = person.count.toString()
    }
}