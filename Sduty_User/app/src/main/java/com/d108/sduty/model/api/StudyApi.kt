package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Study
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyApi {
    @POST("/study")
    suspend fun create(@Body study: Study): Response<Void>

    @GET("/sutdy/check/{study_name}")
    suspend fun getUsedName(@Path("study_name")studyName: String): Response<Boolean>

    @GET("/study/{user_seq}")
    suspend fun myStudyList(@Path("user_seq")userSeq: Int): Response<List<Study>>

    @GET("/study")
    suspend fun studyList(): Response<List<Study>>
}