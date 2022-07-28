package com.d108.sduty.model.dto

data class Task(
    val seq: Int,
    val title: String,
    val content: String,
    val endTime: String,
    val startTime: String,
    val durationTime: String
)