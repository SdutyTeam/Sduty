package com.d108.sduty.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ListItemMystudyBinding

class StudyListAdapter(var list: List<String>): RecyclerView.Adapter<StudyListAdapter.ViewHolder>() {
    var studyList: List<String> = list
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ListItemMystudyBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    interface ClickListener{
        fun onClick(view: View, position: Int)
    }
    lateinit var clickListener: ClickListener
}