package com.d108.sduty_admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty_admin.databinding.ItemReplyBinding
import com.d108.sduty_admin.model.dto.Reply

class ReplyAdapter: RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {
    var list = mutableListOf<Reply>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemReplyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Reply){
            binding.apply {
                data = list[absoluteAdapterPosition]
                btnOption.setOnClickListener {
                    onClickReplyListener.onClick(item)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemReplyBinding = ItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemReplyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    lateinit var onClickReplyListener: OnClickReplyListener
    interface OnClickReplyListener{
        fun onClick(item: Reply)
    }
}