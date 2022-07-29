package com.d108.sduty.model.dto

data class Task(
    val seq: Int,
    val endTime: String,
    val startTime: String,
    val durationTime: String,
    val title: String,
    val content1: String,
    val content2: String,
    val content3: String
)