package com.d108.sduty.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.d108.sduty.R
import com.d108.sduty.common.SERVER_URL

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, src: String?){
    Glide.with(view.context)
        .load(Uri.parse("${SERVER_URL}/image/${src}"))
        .error(R.drawable.empty_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
