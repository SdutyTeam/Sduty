package com.d108.sduty.model.dto

import java.io.Serializable
import java.util.*

data class Study(
    var seq: Int,
    var masterSeq: Int,
    var name: String,
    var introduce: String,
    var category: String,
    var limitNumber: Int,
    var joinNumber: Int,
    var password: String,
    var studyRegtime: Date?,
    var notice: String,
    var camstudy: Boolean
): Serializable {
}