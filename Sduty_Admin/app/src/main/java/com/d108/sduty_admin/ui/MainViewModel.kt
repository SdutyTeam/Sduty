package com.d108.sduty_admin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d108.sduty_admin.model.dto.Admin

class MainViewModel: ViewModel() {

    private val _visibilityBottomNav = MutableLiveData<Boolean>(false)
    val visibilityBottomNav : LiveData<Boolean>
        get() = _visibilityBottomNav
    // true ? show : gone
    fun setVisibilityBottomNav(show: Boolean){
        _visibilityBottomNav.postValue(show)
    }

    private val _admin = MutableLiveData<Admin>()
    val admin: LiveData<Admin>
        get() = _admin
    fun setAdminValue(admin: Admin){ _admin.postValue(admin)}
}