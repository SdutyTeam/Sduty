package com.d108.sduty.model.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("/profile/check/{nickname}")
    suspend fun checkNickName(@Path("nickname") nickname: String): Response<Void>


}