package com.d108.sduty.model.dto

import java.util.*

class Reply(var seq: Int,
            var storySeq: Int,
            var writerSeq: Int,
            var mentionedSeq: Int,
            var content: String,
            var regTime: Date,
            var user: User
) {
}