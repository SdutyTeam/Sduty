package com.d108.sduty.common

import android.app.Application
import android.content.SharedPreferences
import com.d108.sduty.R
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import com.sendbird.calls.SendBirdCall
import net.nurigo.sdk.NurigoApp
import net.nurigo.sdk.message.service.DefaultMessageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass : Application() {
    companion object{
        lateinit var retrofit: Retrofit
        lateinit var messageService: DefaultMessageService
        lateinit var settingsPref: SharedPreferences
        lateinit var userPref: SharedPreferences
        lateinit var pushStateAll: SharedPreferences
        lateinit var pushStatePersonal: SharedPreferences
        lateinit var autoLoginState: SharedPreferences
        lateinit var darkModeState: SharedPreferences
        lateinit var timerPref: SharedPreferences
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

        messageService = NurigoApp.initialize(SOLAPI_API_KEY, SOLAPI_API_SECRET_KEY, SOLAPI_DOMAIN)

        userPref = getSharedPreferences("User", MODE_PRIVATE)
        pushStateAll = getSharedPreferences("PushStateAll", MODE_PRIVATE)
        pushStatePersonal = getSharedPreferences("PushStatePersonal", MODE_PRIVATE)
        darkModeState = getSharedPreferences("DarkMode", MODE_PRIVATE)
        timerPref = getSharedPreferences("Timer", MODE_PRIVATE)
    }
}