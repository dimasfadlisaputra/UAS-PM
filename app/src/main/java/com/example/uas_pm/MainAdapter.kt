package com.example.uas_pm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_pm.databinding.ItemLayoutBinding

class MainAdapter(private val click:(ResponsePost.ResponsePostItem) -> Unit,private val data:MutableList<ResponsePost.ResponsePostItem> = mutableListOf()): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    class MainViewHolder(private val v:ItemLayoutBinding) :RecyclerView.ViewHolder(v.root){
        fun bind(model:ResponsePost.ResponsePostItem) {
            v.title.text = model.title
            v.des.text = model.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
      holder.bind(data[position])
        holder.itemView.setOnClickListener {
            click(data[position])
        }
    }

    fun setData(data:MutableList<ResponsePost.ResponsePostItem>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = data.size
}