package com.d108.sduty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
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
        _visibilityBottomNav.value = hide
    }

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user
    fun setUserValue(user: User){
        _user.postValue(user)
    }

}