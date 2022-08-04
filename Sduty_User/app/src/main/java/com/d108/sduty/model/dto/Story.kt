package com.d108.sduty.model.dto

import java.util.*

data class Story(
    val seq: Int,
    var writerSeq: Int,
    var imageSource: String,
    var thumbnail: String,
    var content: String,
    var regtime: Date?,
    var storyPublic: Int,
    var storyWarning: Int,
) {
    constructor():
            this(0,0,",",",","",null,0,0)
    constructor(seq: Int):
            this(seq,0,"","","",null, 0, 0)
    constructor(writerSeq: Int, imageSource: String, content: String, storyPublic: Int): this(
        0, writerSeq, imageSource, "", content, null, storyPublic, 0)

}