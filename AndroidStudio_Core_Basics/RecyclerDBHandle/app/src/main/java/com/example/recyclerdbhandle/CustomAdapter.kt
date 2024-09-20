package com.example.recyclerdbhandle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    private var stdList : ArrayList<ItemViewModel> = ArrayList()
    private var onClickItem : ((ItemViewModel) -> Unit)? =null
    private var onClickDeleteItem : ((ItemViewModel) -> Unit)? =null
    fun addItems(items : ArrayList<ItemViewModel>){
        this.stdList=items
        notifyDataSetChanged()
    }
    fun setOnClickItem(callback: (ItemViewModel)->Unit){
        this.onClickItem=callback
    }
    fun setOnClickDelete(callback: (ItemViewModel) -> Unit){
        this.onClickDeleteItem=callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
      var view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design,parent,false)
      return ViewHolder(view)
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{onClickDeleteItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
        return stdList.size
    }
    class ViewHolder(var view: View ) : RecyclerView.ViewHolder(view){
        private var id: TextView = view.findViewById(R.id.tvID)
        private var name: TextView = view.findViewById(R.id.tvName)
        private var age: TextView = view.findViewById(R.id.tvAge)
        var btnDelete: Button = view.findViewById(R.id.btnDelete)

        fun bindView(std : ItemViewModel){
            id.text = std.id.toString()
            name.text = std.name
            age.text = std.age.toString()
        }
    }
}