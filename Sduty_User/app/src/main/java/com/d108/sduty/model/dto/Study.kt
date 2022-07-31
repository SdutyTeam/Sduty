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
) {
    constructor(
        masterSeq: Int, name: String, introduce: String, category: String, limitNumber: Int,
        password: String, camstudy: Boolean):
            this(0, masterSeq, name, introduce, category,
            limitNumber, 1, password, null, "", camstudy)



}