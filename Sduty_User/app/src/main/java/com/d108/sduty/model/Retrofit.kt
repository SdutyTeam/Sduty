package com.d108.sduty.model

import com.d108.sduty.common.ApplicationClass
import com.d108.sduty.model.api.TimerApi
import com.d108.sduty.model.api.UserApi

object Retrofit {
    private val retrofit = ApplicationClass.retrofit

    val userApi : UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    val timerApi : TimerApi by lazy {
        retrofit.create(TimerApi::class.java)
    }

}