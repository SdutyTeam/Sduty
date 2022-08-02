package com.d108.sduty.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemTimelineBinding
import com.d108.sduty.model.dto.Story

class TimeLineAdapter(val activity: Activity): RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {
    var list = mutableListOf<Story>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemTimelineBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                ivFavorite.setOnClickListener {
                    onClickTimelineItem.onFavoriteClicked(it, adapterPosition)
                }
                ivMenu.setOnClickListener {
                    onClickTimelineItem.onMenuClicked(it, adapterPosition)
                }
                ivScrap.setOnClickListener {
                    onClickTimelineItem.onScrapClicked(it, adapterPosition)
                }
                ivProfile.setOnClickListener {
                    onClickTimelineItem.onProfileClicked(it, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTimelineBinding =
            ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemTimelineBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        val displaymetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels
        val deviceHeight = deviceWidth / 3 * 4
        holder.binding.apply {
            ivTimelineContent.layoutParams.width = deviceWidth
            ivTimelineContent.layoutParams.height = deviceHeight
        }
    }

    override fun getItemCount(): Int = list.size

    interface TimeLineClickListener{
        fun onFavoriteClicked(view: View, position: Int)
        fun onScrapClicked(view: View, position: Int)
        fun onReplyClicked(view: View, position: Int)
        fun onMenuClicked(view: View, position: Int)
        fun onProfileClicked(view: View, position: Int)
    }
    lateinit var onClickTimelineItem: TimeLineClickListener

}