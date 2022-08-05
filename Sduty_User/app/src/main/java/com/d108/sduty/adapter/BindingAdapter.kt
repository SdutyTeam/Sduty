package com.d108.sduty.adapter

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.d108.sduty.R
import com.d108.sduty.common.SERVER_URL
import com.d108.sduty.model.dto.Profile
private const val TAG ="BindingAdapter"

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, src: String?){
    Glide.with(view.context)
        .load(Uri.parse("${SERVER_URL}/image/${src}"))
        .error(R.drawable.empty_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

@BindingAdapter("hashTagText")
fun hashTagText(view: TextView, profile: Profile?){
    var interest = ""
    if(profile == null) return

    if(!profile.interestHashtags.isNullOrEmpty()){
        for(item in profile.interestHashtags!!){
            interest = "$interest ${item.name}"
        }
    }
    view.text = "${profile.job} /$interest"
}