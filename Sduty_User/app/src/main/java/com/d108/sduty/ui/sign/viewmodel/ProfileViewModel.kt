package com.d108.sduty.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.common.BIRTH_BUTTON
import com.d108.sduty.common.INTEREST_BUTTON
import com.d108.sduty.common.JOB_BUTTON
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG ="ProfileViewModel"
class ProfileViewModel: ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile

    private val _isUsedNickname = MutableLiveData<Boolean>(false)
    val isUsedNickname: LiveData<Boolean>
        get() = _isUsedNickname
    fun checkNickname(nickname: String){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.profileApi.getUsedId(nickname).let {
                if(it.code() == 401){
                    _isUsedNickname.postValue(true)
                }else if(it.code() == 200){
                    _isUsedNickname.postValue(false)
                }else{
                    Log.d(TAG, "checkNickname: ${it}")
                }
            }
        }
    }

    fun insertProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.profileApi.insertProfile(profile).let {
                when {
                    it.code() == 401 -> {
                        _isUsedNickname.postValue(true)
                    }
                    it.code() == 200 -> {
                        _isUsedNickname.postValue(false)
                    }
                    else -> {
                        Log.d(TAG, "checkNickname: ${it}")
                    }
                }
            }
        }
    }

    private val _flagJob = MutableLiveData<Int>(2)
    val flagJob: LiveData<Int>
        get() = _flagJob
    private val _flagInterest = MutableLiveData<Int>(2)
    val flagInterest: LiveData<Int>
        get() = _flagInterest
    private val _flagBirth = MutableLiveData<Int>(2)
    val flagBirth: LiveData<Int>
        get() = _flagBirth

    fun setPublicFlag(clicked: Int){
        Log.d(TAG, "setPublicFlag: $clicked")
        when(clicked){
            JOB_BUTTON -> {
                _flagJob.postValue((flagJob.value!! + 1) % 3)
            }
            INTEREST_BUTTON -> {
                _flagInterest.postValue((flagInterest.value!! + 1) % 3)
            }
            BIRTH_BUTTON -> {
                _flagBirth.postValue((flagBirth.value!! + 1) % 3)
            }
        }
    }
}