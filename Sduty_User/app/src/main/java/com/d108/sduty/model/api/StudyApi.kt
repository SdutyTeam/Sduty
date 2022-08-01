package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Study
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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




}