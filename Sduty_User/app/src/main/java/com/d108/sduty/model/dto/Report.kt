package com.d108.sduty.model.dto

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Report(
    val seq: Int,
    val owner_seq: Int,
    val date: String,
    val total_time: String,
    val tasks: List<Task>
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}