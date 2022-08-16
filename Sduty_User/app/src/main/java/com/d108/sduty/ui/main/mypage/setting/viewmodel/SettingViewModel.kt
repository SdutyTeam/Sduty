package com.d108.sduty.ui.main.mypage.setting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.User
import com.d108.sduty.utils.SettingsPreference
import com.d108.sduty.utils.sharedpreference.FCMPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                user.fcmToken = FCMPreference().getFcmToken()
                user.userPublic = SettingsPreference().getPushState()
                user.regtime = null
                Retrofit.userApi.updateUser(user).let {
                    if(it.isSuccessful){
                        Log.d(TAG, "updateFCMToken: ${it.code()}")
                    }else{
                        Log.d(TAG, "updateFCMToken: ${it.code()}")
                    }
                }

            }catch (e: Exception){
                Log.d(TAG, "updateFCMToken: ${e.message}")
            }
        }
    }

    private val _isAutoLoginEnable = MutableLiveData<Boolean>(true)
    val isAutoLoginEnable: LiveData<Boolean>
        get() = _isAutoLoginEnable
    fun setAutoLoginState(){
        _isAutoLoginEnable.value = !_isAutoLoginEnable.value!!
    }
}