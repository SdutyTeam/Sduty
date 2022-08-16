package com.d108.sduty_admin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _visibilityBottomNav = MutableLiveData<Boolean>(false)
    val visibilityBottomNav : LiveData<Boolean>
        get() = _visibilityBottomNav
    // true ? show : gone
    fun setVisibilityBottomNav(show: Boolean){
        _visibilityBottomNav.postValue(show)
    }
}