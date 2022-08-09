package com.d108.sduty.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemTimelineBinding
import com.d108.sduty.model.dto.Timeline

private const val TAG ="TimeLinePagingAdapter"
class TimeLinePagingAdapter(val activity: Activity): PagingDataAdapter<Timeline, TimeLinePagingAdapter.ViewHolder>(IMAGE_COMPARATOR) {
    inner class ViewHolder(val binding: ItemTimelineBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(timeline: Timeline){
            binding.apply {
                data = timeline
                ivFavorite.setOnClickListener {
                    onClickTimelineItem.onFavoriteClicked(it, absoluteAdapterPosition)
                }
                ivMenu.setOnClickListener {
                    onClickTimelineItem.onMenuClicked(it, absoluteAdapterPosition)
                }
                ivScrap.setOnClickListener {
                    onClickTimelineItem.onScrapClicked(it, absoluteAdapterPosition)
                }
                ivProfile.setOnClickListener {
                    onClickTimelineItem.onProfileClicked(it, absoluteAdapterPosition)
                }
                constComments.setOnClickListener {
                    onClickTimelineItem.onReplyClicked(it, absoluteAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTimelineBinding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemTimelineBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(getItem(position) !=null) {
            holder.bind(getItem(position)!!)
        }
        val displaymetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels
        val deviceHeight = deviceWidth / 3 * 4
        holder.binding.apply {
            ivTimelineContent.layoutParams.width = deviceWidth
            ivTimelineContent.layoutParams.height = deviceHeight
        }
    }

    interface TimeLineClickListener{
        fun onFavoriteClicked(view: View, position: Int)
        fun onScrapClicked(view: View, position: Int)
        fun onReplyClicked(view: View, position: Int)
        fun onMenuClicked(view: View, position: Int)
        fun onProfileClicked(view: View, position: Int)
    }
    lateinit var onClickTimelineItem: TimeLineClickListener

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<Timeline>() {
            override fun areItemsTheSame(oldItem: Timeline, newItem: Timeline) =
                oldItem.story.seq == newItem.story.seq

            override fun areContentsTheSame(oldItem: Timeline, newItem: Timeline) =
                oldItem == newItem
        }
    }
}