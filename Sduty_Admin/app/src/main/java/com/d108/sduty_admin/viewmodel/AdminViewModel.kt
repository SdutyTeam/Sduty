package com.d108.sduty_admin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty_admin.model.Retrofit
import com.d108.sduty_admin.model.dto.Admin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG ="AdminViewModel"
class AdminViewModel: ViewModel() {
    private val _admin = MutableLiveData<Admin>()
    val admin: LiveData<Admin>
        get() = _admin
    
    fun login(admin: Admin){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.adminApi.login(admin).let { 
                if(it.isSuccessful && it.body() != null){
                    _admin.postValue(it.body() as Admin)
                }else{
                    Log.d(TAG, "login: ${it.code()}")
                }
            }

        }
    }
    
}