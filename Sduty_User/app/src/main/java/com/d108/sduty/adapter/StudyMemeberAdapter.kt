package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ListItemStudyMemberBinding
import com.d108.sduty.model.dto.Member


class StudyMemeberAdapter(var list: List<Member>): RecyclerView.Adapter<StudyMemeberAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemStudyMemberBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Member){
            binding.studyMember = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val memberlistStudyItemBinding =
            ListItemStudyMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(memberlistStudyItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size


}