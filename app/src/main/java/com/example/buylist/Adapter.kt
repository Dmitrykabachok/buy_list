package com.example.buylist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class Adapter(private val listener: (Any) -> (Unit)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapterList: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(adapterList[position])
        holder.deleteButton.setOnClickListener { listener(adapterList[position]) }
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun setData(data: ArrayList<Item>) {
        this.adapterList = data
        notifyDataSetChanged()
    }

    fun deleteData(item: Item) {
        adapterList.remove(item)
        notifyDataSetChanged()
    }
}