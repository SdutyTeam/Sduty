package com.d108.sduty.ui.main.mypage.setting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.common.ApplicationClass
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.User
import com.d108.sduty.utils.SettingsPreference
import com.d108.sduty.utils.sharedpreference.FCMPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG ="SettingViewModel"
class SettingViewModel: ViewModel() {
    private val _isSucceedResign = MutableLiveData<Boolean>()
    val isSucceedResign: LiveData<Boolean>
        get() = _isSucceedResign
    fun resignUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.userApi.deleteUser(user.seq).let {
                if(it.isSuccessful){
                    _isSucceedResign.postValue(true)
                }else{
                    _isSucceedResign.postValue(false)
                }
            }
        }
    }


    fun updateFCMToken(user: User){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = FCMPreference().getFcmToken()
                val state = SettingsPreference().getPushState()
                user.fcmToken = token
                user.userPublic = state
                Retrofit.userApi.updateUser(user).let {
                    if(it.isSuccessful){
                        
                    }else{
                        Log.d(TAG, "updateFCMToken: ${it.code()}")
                    }
                }

            }catch (e: Exception){
                Log.d(TAG, "insertFCMToken: ${e.message}")
            }
        }
    }
}