package com.d108.sduty_admin.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty_admin.databinding.ItemStoryBinding
import com.d108.sduty_admin.model.dto.Story

class StoryPagingAdapter(private val activity: Activity): PagingDataAdapter<Story, StoryPagingAdapter.ViewHolder>(IMAGE_COMPARATOR) {

    inner class ViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(story: Story){
            binding.apply {
                data = story
                ivStory.setOnClickListener {
                    onClickStoryListener.onClick(story)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(getItem(position) !=null) {
            holder.bind(getItem(position)!!)
        }
        val displaymetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels / 3
        val deviceHeight = deviceWidth * 4 / 3
        holder.binding.apply {
            ivStory.layoutParams.width = deviceWidth
            ivStory.layoutParams.height = deviceHeight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemStoryBinding)
    }

    lateinit var onClickStoryListener: OnClickStoryListener
    interface OnClickStoryListener{
        fun onClick(story: Story)
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story) =
                oldItem.seq == newItem.seq

            override fun areContentsTheSame(oldItem: Story, newItem: Story) =
                oldItem == newItem
        }
    }
}