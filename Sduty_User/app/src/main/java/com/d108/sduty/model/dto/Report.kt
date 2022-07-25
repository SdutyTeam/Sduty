package com.d108.sduty.model.dto

data class Report (
    var reportDate: String,
    var totalTime: String,
    var Tasks: List<Task>
   ){
}