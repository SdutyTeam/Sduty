package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ListItemMystudyBinding
import com.d108.sduty.ui.sign.viewmodel.MyStudyViewModel

class MyStudyAdapter(var list: List<String>) : RecyclerView.Adapter<MyStudyAdapter.ViewHolder>() {
    var myStudyList: List<String> = list
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ListItemMystudyBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.myStudyVM = MyStudyViewModel()
        }
        fun bind(text: String){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemMystudyBinding =
            ListItemMystudyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemMystudyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = myStudyList.size

    interface ClickListener{
        fun onClick(view: View, position: Int)
    }
    lateinit var clickListener: ClickListener





}