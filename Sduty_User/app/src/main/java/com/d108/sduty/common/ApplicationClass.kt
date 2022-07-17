package com.d108.sduty.common

import android.app.Application
import com.d108.sduty.R
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import com.sendbird.calls.SendBirdCall
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass : Application() {
    companion object{
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, resources.getString(R.string.kakao_api_key))

        NaverIdLoginSDK.initialize(this, resources.getString(R.string.naver_client_id),
            resources.getString(R.string.naver_client_secret),
            resources.getString(R.string.app_name))

        SendBirdCall.setLoggerLevel(SendBirdCall.LOGGER_INFO)

        var gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}