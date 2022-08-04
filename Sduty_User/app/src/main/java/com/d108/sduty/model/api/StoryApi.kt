package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Reply
import com.d108.sduty.model.dto.Story
import com.d108.sduty.model.dto.Timeline
import retrofit2.Response
import retrofit2.http.*

interface StoryApi {
    @GET("/story")
    suspend fun getStoryList(): Response<List<Timeline>>

    @POST("/story")
    suspend fun insertStory(@Body story: Story): Response<List<Story>>

    @PUT("/story")
    suspend fun updateStory(@Body story: Story): Response<List<Story>>

    @GET("/story/{story_seq")
    suspend fun getStudyDetail(@Path("story_seq") storySeq: Int): Response<Timeline>

    @GET("/story/writer/{user_seq}")
    suspend fun getUserStoryList(@Path("user_seq")userSeq: Int): Response<List<Story>>

    @GET("/story/scrap/{user_seq}")
    suspend fun getScrapList(@Path("user_seq")userSeq: Int): Response<List<Story>>

    @GET("/story/date/{user_seq}")
    suspend fun getContributionList(@Path("user_seq")userSeq: Int): Response<List<Boolean>>

    @GET("/story/{user_seq}/{hashTag}")
    suspend fun getFilteredStoryList(@Path("user_seq")userSeq: Int, @Path("hashTag")hashTag: String): Response<List<Story>>

    @DELETE("/story/{story_seq}")
    suspend fun deleteStory(@Path("story_seq")storySeq: Int): Response<List<Story>>

    @GET("/story/{story_seq}/reply")
    suspend fun getReplyList(@Path("story_seq")storySeq: Int): Response<List<Reply>>

    @POST("/story/{story_seq}/reply")
    suspend fun insertComment(@Body comment: Reply, @Path("story_seq")storySeq: Int): Response<List<Reply>>

    @PUT("/story/{story_seq}/reply")
    suspend fun updateComment(@Body comment: Reply, @Path("story_seq")storySeq: Int): Response<List<Reply>>

    @DELETE("/story/{story_seq}/reply/{reply_seq}")
    suspend fun deleteComment(@Path("story_seq")storySeq: Int, @Path("reply_seq")replySeq: Int): Response<List<Reply>>

    @PUT("/story/like/{user_seq}")
    suspend fun likeStory(@Path("user_seq")userSeq: Int, @Body story: Story): Response<Void>

    @PUT("/story/scrap/{user_seq}")
    suspend fun scrapStory(@Path("user_seq")userSeq: Int, @Body story: Story): Response<Void>

    @PUT("story/report")
    suspend fun reportStory(@Body story: Story): Response<Void>
}