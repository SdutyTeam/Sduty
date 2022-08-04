package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.common.INTEREST_TAG
import com.d108.sduty.common.JOB_TAG
import com.d108.sduty.databinding.ItemTagBinding
import com.d108.sduty.model.dto.InterestHashtag
import com.d108.sduty.model.dto.JobHashtag

class TagAdapter(val flag: Int): RecyclerView.Adapter<TagAdapter.ViewHolder>() {
    var jobList = mutableListOf<JobHashtag>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var interestList = mutableListOf<InterestHashtag>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(var binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(name: String){
            binding.apply {
                btnTag.text = name
                btnTag.setOnClickListener {
                    onClickTagItem.onClick(it, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTagBinding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemTagBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(flag){
            JOB_TAG -> holder.bind(jobList[position].name)
            INTEREST_TAG -> holder.bind(interestList[position].name)
        }
    }

    override fun getItemCount(): Int {
        when(flag){
            JOB_TAG -> return jobList.size
            INTEREST_TAG -> return interestList.size
        }
        return 0
    }
    interface OnClickTagListener{
        fun onClick(view: View, position: Int)
    }
    lateinit var onClickTagItem: OnClickTagListener

}