package com.d108.sduty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d108.sduty.databinding.ItemFollowBinding
import com.d108.sduty.model.dto.Follow

class FollowAdapter(val userSeq: Int): RecyclerView.Adapter<FollowAdapter.ViewHolder>() {
    var list = mutableListOf<Follow>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemFollowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(follow: Follow){
            binding.apply {
                data = follow
                if(userSeq == follow.followerSeq){
                    btnFollow.text = "취소"
                }else{
                    btnFollow.text = "팔로잉"
                }
                constProfile.setOnClickListener {
                    onClickProfileListener.onClickProfile(follow)
                }
                btnFollow.setOnClickListener {
                    onClickFollowListener.onClickFollowBtn(follow)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemFollowBinding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemFollowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    lateinit var onClickFollowListener: OnClickFollowListener
    lateinit var onClickProfileListener: OnClickFollowListener
    interface OnClickFollowListener{
        fun onClickFollowBtn(follow: Follow)
        fun onClickProfile(follow: Follow)
    }
}