package com.d108.sduty_admin.adapter

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.d108.sduty_admin.R
import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.common.SERVER_URL
import com.d108.sduty_admin.model.dto.InterestHashtag

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, src: String?) {
    Glide.with(view.context)
        .load(Uri.parse("${SERVER_URL}/image/${src}"))
        .error(R.drawable.empty_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

@BindingAdapter("interestHashTagText")
fun interestHashTagText(view: TextView, list: MutableList<InterestHashtag>?){
    if(!list.isNullOrEmpty()){
        var text = ""
        for(item in list!!){
            text = "${text} #${item.name} "
        }
        view.text = text
    }else{
        view.text = ""
    }
}

@BindingAdapter("jobSeqToJobName")
fun jobSeqToJobName(view: TextView, jobSeq: Int){
    view.text = ApplicationClass.jobTagMap[jobSeq]
}