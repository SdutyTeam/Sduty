package com.d108.sduty_admin.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.d108.sduty_admin.R
import com.d108.sduty_admin.common.SERVER_URL

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, src: String?) {
    Glide.with(view.context)
        .load(Uri.parse("${SERVER_URL}/image/${src}"))
        .error(R.drawable.empty_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
