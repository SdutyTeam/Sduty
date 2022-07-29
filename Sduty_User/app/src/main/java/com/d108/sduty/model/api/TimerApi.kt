package com.d108.sduty.model.api

import com.d108.sduty.model.dto.Report
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface TimerApi {

    // 사용자가 선택한 날짜의 리포트 목록을 받아온다.
    @GET("/report/{user_seq}/{date}")
    suspend fun getReport(@Path("user_seq") user_seq : Int, @Path("date") date: Date): Response<Report>

    // 태스크를 등록한다.
    @POST("/report/tasks")
    suspend fun postTask(@Body report: Report): Response<Unit>
}