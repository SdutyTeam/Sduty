package com.d108.sduty_admin.common

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass: Application() {
    companion object{
        lateinit var retrofit: Retrofit
        lateinit var jobTagMap: HashMap<Int, String>
    }

    override fun onCreate() {
        super.onCreate()
        var gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

}