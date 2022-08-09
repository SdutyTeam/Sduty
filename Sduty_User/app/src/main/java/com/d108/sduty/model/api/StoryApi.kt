package com.d108.sduty.model.api

import com.d108.sduty.model.dto.*
import com.d108.sduty.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.sql.Time

interface StoryApi {
    @GET("/story/all/{userSeq}")
    suspend fun getStoryList(@Path("userSeq")userSeq: Int): Response<List<Timeline>>

    @Multipart
    @POST("/story")
    suspend fun insertStory(@Part imageFile: MultipartBody.Part, @Part("story") story: RequestBody): Response<Timeline>

    @PUT("/story")
    suspend fun updateStory(@Body story: Story): Response<Timeline>

    @GET("/story/{story_seq}")
    suspend fun getTimelineDetail(@Path("story_seq") storySeq: Int): Response<Timeline>

    @GET("/story/writer/{user_seq}")
    suspend fun getUserStoryList(@Path("user_seq")userSeq: Int): Response<List<Story>>

    @GET("/story/scrap/{user_seq}")
    suspend fun getScrapList(@Path("user_seq")userSeq: Int): Response<List<Story>>

    @GET("/profile/chart/{userSeq}")
    suspend fun getContributionList(@Path("userSeq")userSeq: Int): Response<List<Boolean>>

    @GET("/story/user/{userSeq}/{jobName}")
    suspend fun getStoryJobAndFollowList(@Path("userSeq")userSeq: Int, @Path("jobName")jobName: String): Response<MutableList<Timeline>>

    @GET("/story/job/{userSeq}/{jobName}")
    suspend fun getStoryJobAndAllList(@Path("userSeq")userSeq: Int, @Path("jobName")jobName: String): Response<MutableList<Timeline>>

    @GET("/story/interest/{userSeq}/{interestName}")
    suspend fun getStoryInterestAndAllList(@Path("userSeq")userSeq: Int, @Path("interestName")interestName: String): Response<MutableList<Timeline>>

    @GET("/story/recommand/{userSeq}")
    suspend fun getStoryRecommended(@Path("userSeq") userSeq: Int): Response<Timeline>

    @DELETE("/story/{story_seq}")
    suspend fun deleteStory(@Path("story_seq")storySeq: Int): Response<Void>

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

    @PUT("/story/report")
    suspend fun reportStory(@Body story: Story): Response<Void>

    @GET("/story/test")
    suspend fun getAllPagingTimeline(@Query("userSeq")userSeq: Int, @Query("page")page: Int): Response<List<Timeline>>
//    suspend fun getAllPagingTimeline(@Query("userSeq")userSeq: Int, @Query("page")page: Int): Response<List<Timeline>>
}