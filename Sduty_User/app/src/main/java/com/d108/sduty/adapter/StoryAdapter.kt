package com.d108.sduty.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemStoryBinding

class StoryAdapter: RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    var list = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}