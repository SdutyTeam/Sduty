package com.d108.sduty.model.dto

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val seq: Int,
    val reportSeq: Int,
    val endTime: String,
    val startTime: String,
    val durationTime: Int,
    val title: String,
    val content1: String,
    val content2: String,
    val content3: String
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {}
}