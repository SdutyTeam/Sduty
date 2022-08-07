package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemReplyBinding
import com.d108.sduty.model.dto.Reply

class ReplyAdapter: RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {
    var list = mutableListOf<Reply>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemReplyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.data = list[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemReplyBinding = ItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemReplyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size
}