package com.d108.sduty.model

import com.d108.sduty.common.ApplicationClass
import com.d108.sduty.model.api.*

object Retrofit {
    private val retrofit = ApplicationClass.retrofit

    val userApi : UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
    val profileApi: ProfileApi by lazy {
        retrofit.create(ProfileApi::class.java)
    }

    val timerApi : TimerApi by lazy {
        retrofit.create(TimerApi::class.java)
    }

    val studyApi : StudyApi by lazy{
        retrofit.create(StudyApi::class.java)
    }

    val storyApi: StoryApi by lazy {
        retrofit.create(StoryApi::class.java)
    }

}