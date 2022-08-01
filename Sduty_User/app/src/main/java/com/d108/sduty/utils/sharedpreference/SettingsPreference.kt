package com.d108.sduty.utils

import com.d108.sduty.common.ApplicationClass
import com.google.gson.Gson

class SettingsPreference {
    companion object

    fun setPushStateAll(accept: Boolean){
        ApplicationClass.pushStateAll.edit().putBoolean("State", accept).apply()
    }
    fun setPushStatePersonal(accept: Boolean){
        ApplicationClass.pushStatePersonal.edit().putBoolean("State", accept).apply()
    }
    fun getPushStateAll(): Boolean{
        return ApplicationClass.pushStateAll.getBoolean("State", true)
    }
    fun getPushStatePersonal(): Boolean{
        return ApplicationClass.pushStatePersonal.getBoolean("State", true)
    }

    fun setAutoLoginState(accept: Boolean){
        ApplicationClass.autoLoginState.edit().putBoolean("State", accept).apply()
    }
    fun getAutoLoginState(): Boolean{
        return ApplicationClass.autoLoginState.getBoolean("State", true)
    }
    fun getDarkModeState(): Boolean{
        return ApplicationClass.darkModeState.getBoolean("State", true)
    }
    fun setDarkModeState(accept: Boolean){
        ApplicationClass.darkModeState.edit().putBoolean("State", accept).apply()
    }
}