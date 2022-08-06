package com.d108.sduty.model.api

import com.d108.sduty.model.dto.*
import com.d108.sduty.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface StoryApi {
    @GET("/story/all/{userSeq}")
    suspend fun getStoryList(@Path("userSeq")userSeq: Int): Response<List<Timeline>>

    @Multipart
    @POST("/story")
    suspend fun insertStory(@Part imageFile: MultipartBody.Part, @Part("story") story: RequestBody): Response<List<Story>>

    @PUT("/story")
    suspend fun updateStory(@Body story: Story): Response<Timeline>

    @GET("/story/{story_seq}")
    suspend fun getTimelineDetail(@Path("story_seq") storySeq: Int): Response<Timeline>

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

    @POST("/story/reply")
    suspend fun insertReply(@Body reply: Reply): Response<MutableList<Reply>>

    @PUT("/story/{story_seq}/reply")
    suspend fun updateReply(@Body reply: Reply, @Path("story_seq")storySeq: Int): Response<MutableList<Reply>>

    @DELETE("/story/{story_seq}/reply/{reply_seq}")
    suspend fun deleteReply(@Path("story_seq")storySeq: Int, @Path("reply_seq")replySeq: Int): Response<MutableList<Reply>>

    @POST("/story/like")
    suspend fun likeStory(@Body likes: Likes): Response<Void>

    @POST("/story/scrap")
    suspend fun scrapStory(@Body scrap: Scrap): Response<Void>

    @PUT("story/report")
    suspend fun reportStory(@Body story: Story): Response<Void>
}