package com.d108.sduty.model.dto

import com.google.gson.annotations.SerializedName

data class Timeline(var profile: Profile, var story: Story, var cntReply: Int, var replies: MutableList<Reply>, var numLikes: Int, var likes: Boolean, var scrap: Boolean, var jobHashtag: JobHashtag, var interestHashtags: List<InterestHashtag>) {
}

data class PagedResponse(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<Character> = listOf()
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)