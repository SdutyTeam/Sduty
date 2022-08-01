package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ListItemMystudyBinding
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.main.study.viewmodel.MyStudyViewModel

class MyStudyAdapter() : RecyclerView.Adapter<MyStudyAdapter.ViewHolder>() {
    var list = mutableListOf<Study>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ListItemMystudyBinding): RecyclerView.ViewHolder(binding.root){
        init {

        }
        fun bind(){
            binding.data = list[adapterPosition]
            binding.root.setOnClickListener {
                clickListener.onClick(it, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemMystudyBinding =
            ListItemMystudyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemMystudyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    interface ClickListener{
        fun onClick(view: View, position: Int)
    }
    lateinit var clickListener: ClickListener





}