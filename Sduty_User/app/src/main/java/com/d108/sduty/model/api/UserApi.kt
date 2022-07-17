package com.d108.sduty.model.api

import com.d108.sduty.model.dto.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @POST("/user")
    suspend fun login(@Body user: User): Response<User>

    @GET("/user/join/{id}")
    suspend fun getUsedId(@Path("id")id: String): Response<Boolean>

    @POST("/user/join")
    suspend fun join(@Body user: User): Response<Void>

    @GET("/user/test")
    suspend fun getTest(): Response<List<String>>

    @POST("/user/kakao")
    suspend fun kakaoLogin(@Body token: String): Response<User>

    @POST("/user/naver")
    suspend fun naverLogin(@Body token: String): Response<User>

    @POST("user/kakao/join")
    suspend fun kakaoJoin(@Body token: String): Response<User>

    @POST("user/naver/join")
    suspend fun naverJoin(@Body token: String): Response<User>
}