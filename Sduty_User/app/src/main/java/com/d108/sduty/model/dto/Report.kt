package com.d108.sduty.model.dto

import java.util.*

data class Report (
    var reportDate: Date,
    var totalTime: String,
    var Tasks: List<Task>
   ){
}