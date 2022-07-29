package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Profile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileApi {
    @GET("/profile/check/{nickname}")
    suspend fun getUsedId(@Path("nickname") nickname: String): Response<Void>

    @POST("/profile")
    suspend fun insertProfile(@Part imageFile: MultipartBody.Part, @Part("banner") banner: RequestBody) : Response<Profile>

}