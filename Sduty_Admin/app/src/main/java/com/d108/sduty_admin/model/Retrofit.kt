package com.d108.sduty_admin.model

import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.model.api.AdminApi

object Retrofit {
    private val retrofit = ApplicationClass.retrofit

    val adminApi: AdminApi by lazy {
        retrofit.create(AdminApi::class.java)
    }


}