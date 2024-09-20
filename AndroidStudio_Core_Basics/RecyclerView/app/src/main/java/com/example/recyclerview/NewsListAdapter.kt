package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

// in adapter we have to put viewHolder class
// so we hava to create viewHolder class
// new list-adapter requires the data through the constructor
class NewsListAdapter(private val items : ArrayList<String>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // converting from xml to viewformat in the NewsViewHolder by crating instance of it
        val views = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return NewsViewHolder(views)
        // called when we r creating view holder
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        // taking itme one by one in it to help them bind together
        // taking the item at current position by the help of holder
        val currentItem = items[position]
        holder.title.text = currentItem
    }
    override fun getItemCount(): Int {
        return items.size
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // now craete item in the list that show on the layout
    // this is the text view which is showing in the
    val title = itemView.findViewById<TextView>(R.id.title)
}