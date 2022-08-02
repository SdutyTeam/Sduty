package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Study
import retrofit2.Response
import retrofit2.http.*

interface StudyApi {
    @POST("/study")
    suspend fun studyCreate(@Body study: Study): Response<Study>

    @GET("/study/check/{study_name}")
    suspend fun getStudyName(@Path("study_name")studyName: String): Response<Void>

    @GET("/study")
    suspend fun studyList(): Response<List<Study>>

    @GET("/study/{study_seq}")
    suspend fun getStudyDetail(@Path("study_seq")studySeq: Int): Response<Study>

    @GET("/study/{user_seq}")
    suspend fun myStudyList(@Path("user_seq")userSeq: Int): Response<List<Study>>


    @GET("study/filter/{category}/{emptyfilter}/{camfilter}/{publicfilter}")
    suspend fun studyFilter(@Path("category")category: String, @Path("emptyfilter")emptyFilter: Boolean,
                            @Path("camfilter")camFilter: Boolean, @Path("publicfilter")publicFilter: Boolean): Response<List<Study>>

    @GET("/study/filter/{keyword}")
    suspend fun studySearch(@Path("keyword")keyword: String) : Response<List<Study>>

    // 스터디 삭제
    @DELETE("/study/{user_seq}/{study_seq}")
    suspend fun deleteStduy(@Path("user_seq")userSeq: Int, @Path("study_seq")studySeq: Int): Response<Void>

    // 스터디 탈퇴
    @DELETE("/study/participation/{study_seq}/{user_seq}")
    suspend fun quitStduy(@Path("study_seq")studySeq: Int, @Path("user_seq")userSeq: Int): Response<Void>

}