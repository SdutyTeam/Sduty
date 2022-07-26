package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Profile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApi {
    @GET("/profile/check/{nickname}")
    suspend fun getUsedId(@Path("nickname") nickname: String): Response<Void>

    @POST("/profile")
    suspend fun insertProfile(@Body profile: Profile): Response<Void>

}