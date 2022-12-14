package com.d108.sduty_admin.model.api

import com.d108.sduty_admin.model.dto.*
import com.d108.sduty_admin.model.paging.PagingResult
import retrofit2.Response
import retrofit2.http.*

interface AdminApi {
    @POST("/admin/login")
    suspend fun login(@Body admin: Admin): Response<Admin>

    @POST("/admin/notice")
    suspend fun insertNotice(@Body notice: Notice): Response<Void>

    @PUT("/admin/notice/{notice_seq}")
    suspend fun updateNotice(@Body notice: Notice, @Path("notice_seq") seq: Int): Response<Notice>

    @DELETE("/admin/notice/{notice_seq}")
    suspend fun deleteNotice(@Path("notice_seq")seq: Int): Response<Void>

    @POST("/admin/question")
    suspend fun insertDailyQuestions(@Body dailyQuestion: DailyQuestion): Response<Void>

    @GET("/admin/question")
    suspend fun getDailyQuestion(): Response<MutableList<DailyQuestion>>

    @GET("/admin/question/{question_seq}")
    suspend fun getDailyQuestionDetail(@Path("question_seq")seq: Int): Response<DailyQuestion>

    @PUT("/admin/question")
    suspend fun updateDailyQuestion(@Body dailyQuestion: DailyQuestion): Response<DailyQuestion>

    @DELETE("/admin/question/{question_seq}")
    suspend fun deleteDailyQuestion(@Path("question_seq")seq: Int): Response<Void>

    // 1:1문의 게시판?? 파이어베이스??
    @GET("/admin/qna")
    suspend fun getQna(): Response<MutableList<Qna>>

    @GET("/admin/qna/{qna_seq}")
    suspend fun getQnaDeatil(@Path("qna_seq")seq: Int): Response<Qna>

    @POST("/admin/qna")
    suspend fun updateQna(@Body qna: Qna): Response<Qna>

    @DELETE("/admin/qna/{qna_seq}")
    suspend fun deleteQna(@Path("qna_seq")seq: Int): Response<Void>

    @GET("/admin/bad-story")
    suspend fun getReportStory(@Query("page") page: Int, @Query("size") pageSize: Int): Response<PagingResult<Story>>

    @GET("/story/{storySeq}/{userSeq}")
    suspend fun getTimelineDetail(@Path("storySeq") storySeq: Int, @Path("userSeq")userSeq: Int): Response<Timeline>

    @GET("/tag/job")
    suspend fun getJobList(): Response<List<JobHashtag>>

    @DELETE("/story/{story_seq}")
    suspend fun deleteStory(@Path("story_seq")storySeq: Int): Response<Void>

    @DELETE("/story/reply/{reply_seq}")
    suspend fun deleteReply(@Path("reply_seq")replySeq: Int): Response<MutableList<Reply>>

    @GET("/setting/notice")
    suspend fun getNoticeList(): Response<List<Notice>>

    @POST("/admin/fcm")
    suspend fun sendFCM(@Body message: Map<String, String>): Response<Void>

    @GET("/admin/fcm/{userSeq}")
    suspend fun sendFCMOne(@Path("userSeq") userSeq: Int): Response<Void>
}