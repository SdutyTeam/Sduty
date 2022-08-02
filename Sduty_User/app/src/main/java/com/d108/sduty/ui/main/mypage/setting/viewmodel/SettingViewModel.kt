package com.d108.sduty.ui.main.mypage.setting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
}