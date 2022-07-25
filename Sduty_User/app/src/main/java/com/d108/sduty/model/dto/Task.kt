package com.d108.sduty.model.dto

data class Task(
    var seq : Int,
    var title : String,
    var content : String,
    var startTime : String,
    var endTime : String,
    var duration: String
){
}