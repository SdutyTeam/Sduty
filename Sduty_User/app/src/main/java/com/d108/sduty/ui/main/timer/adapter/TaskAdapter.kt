package com.d108.sduty.ui.main.timer.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.R
import com.d108.sduty.databinding.ListItemTaskBinding
import com.d108.sduty.model.dto.Task

class TaskListAdapter(val fragmentActivity: FragmentActivity, var list: List<Task>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private var colorPalette = listOf(
        R.color.sduty_light_blue,
        R.color.sduty_main_blue,
        R.color.sduty_light_purple,
        R.color.sduty_main_purple
    )

    inner class ViewHolder(private val binding: ListItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task, position: Int) {
            binding.task = item
            binding.taskColor = colorPalette[position % 4]
            Log.d("TEST", "bind: ${position % 4}")
        }

        fun bindClickListener(listener: OnClickTaskItem) {
            binding.root.setOnClickListener {
                listener.onClick(it, fragmentActivity, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemTaskBinding =
            ListItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemTaskBinding).apply {
            bindClickListener(onClickTaskItem)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = list.size


    interface OnClickTaskItem {
        fun onClick(view: View, fragmentActivity: FragmentActivity, position: Int)
    }

    lateinit var onClickTaskItem: OnClickTaskItem
}