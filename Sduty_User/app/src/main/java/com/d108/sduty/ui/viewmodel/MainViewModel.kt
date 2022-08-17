package com.d108.sduty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.model.dto.User
import com.d108.sduty.utils.SettingsPreference
import com.d108.sduty.utils.convertTimeStringToDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer

private const val TAG ="MainViewModel"
class MainViewModel: ViewModel() {
    private val _visibilityBottomNav = MutableLiveData<Boolean>(false)
    val visibilityBottomNav : LiveData<Boolean>
        get() = _visibilityBottomNav
    // true ? show : gone
    fun displayBottomNav(show: Boolean){
        _visibilityBottomNav.postValue(show)
    }

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user
    fun setUserValue(user: User){
        _user.postValue(user)
    }
    fun getUserValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.userApi.getUserValue(userSeq).let {
                if(it.isSuccessful && it.body() != null) {
                    _user.postValue(it.body() as User)
                }else{
                    Log.d(TAG, "getUserValue: ${it.code()}")
                }
            }
        }
    }

    private val _isRegisterProfile = MutableLiveData<Boolean>()
    val isRegisterProfile: LiveData<Boolean>
        get() = _isRegisterProfile
    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile
    fun setProfileValue(profile: Profile){ _profile.postValue(profile)}
    fun getProfileValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.profileApi.getProfileValue(userSeq).let {
                if(it.isSuccessful && it.body() != null) {
                    _profile.postValue(it.body() as Profile)
                    _isRegisterProfile.postValue(true)
                }else{
                    _isRegisterProfile.postValue(false)
                    Log.d(TAG, "getProfileValue: ${it.code()}")
                }
            }
        }
    }

    // 측정 중인 시간
    private val _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int>
        get() = _timer
    private var timerTask: Timer? = null

    // 시간 측정을 시작한다.
    fun startTimer() {
        val state = SettingsPreference().getAppUseTimeState()

        if(state){
            val time = SettingsPreference().getAppUseTimeValue()
            val timeValue = convertTimeStringToDate(time, "HH:mm:ss").time.toInt()
            timerTask = timer(period = 1000) {
                _timer.postValue(timer.value!! + 1)
                if(_timer.value == timeValue){
                    //다이얼로그 출력
                }
            }
        }

    }

    fun checkFollowUser(userSeq: Int): Boolean{
        Log.d(TAG, "checkFollowUser: ${userSeq}  profile:  ${_profile.value!!.follows}")
        return _profile.value!!.follows?.get("${userSeq}")!= null
    }

    fun clear(){
        _user.value = null
        _profile.value = null
        _isRegisterProfile.value = false
    }
}