package com.d108.sduty.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemStoryBinding
import com.d108.sduty.model.dto.Story


class StoryAdapter(val activity: Activity): RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    var list = listOf<Story>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Story){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemStoryBinding =
            ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemStoryBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        val displaymetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels / 3
        val deviceHeight = displaymetrics.heightPixels / 4
        holder.binding.apply {
            ivStory.layoutParams.width = deviceWidth
            ivStory.layoutParams.height = deviceHeight
        }
    }

    override fun getItemCount(): Int = list.size
}