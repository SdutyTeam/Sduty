package com.d108.sduty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.model.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG ="MainViewModel"
class MainViewModel: ViewModel() {
    private val _visibilityBottomNav = MutableLiveData<Boolean>(false)
    val visibilityBottomNav : LiveData<Boolean>
        get() = _visibilityBottomNav
    // true ? show : gone
    fun displayBottomNav(hide: Boolean){
        _visibilityBottomNav.postValue(hide)
    }

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user
    fun setUserValue(user: User){
        _user.postValue(user)
    }

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile
    fun setProfileValue(profile: Profile){ _profile.postValue(profile)}
    fun getProfileValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.profileApi.getProfileValue(userSeq).let {
                if(it.isSuccessful && it.body() != null) {
                    _profile.postValue(it.body() as Profile)
                    _isRegisterdProfile.postValue(true)
                }else{
                    _isRegisterdProfile.postValue(false)
                    Log.d(TAG, "getProfileValue: ${it.code()}")
                }
            }
        }
    }

}