package com.d108.sduty.sduty_admin.dto

import android.provider.ContactsContract
import com.d108.sduty_admin.model.dto.InterestHashtag
import com.d108.sduty_admin.model.dto.JobHashtag
import com.d108.sduty_admin.model.dto.Reply
import com.d108.sduty_admin.model.dto.Story

data class Timeline(var profile: ContactsContract.Profile, var story: Story, var cntReply: Int, var replies: MutableList<Reply>, var numLikes: Int, var likes: Boolean, var scrap: Boolean, var jobHashtag: JobHashtag, var interestHashtags: List<InterestHashtag>) {
}