package com.d108.sduty.model.dto

data class Report(
    val report_seq: Int,
    val report_owner: Int,
    val report_date: String,
    val total_time: String,
    val tasks: List<Task>
)