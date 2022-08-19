package com.d108.sduty_admin.ui.login

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty_admin.model.dto.Admin
import com.d108.sduty_admin.model.repository.Repository
import com.d108.sduty_admin.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

private const val TAG = "LoginViewModel"
class LoginViewModel: ViewModel() {
    private val repository = Repository.get()

    private val _isLoginSucceed = MutableLiveData<Boolean>()
    val isLoginSucceed: LiveData<Boolean>
        get() = _isLoginSucceed

    private val _admin = MutableLiveData<Admin>()
    val admin : LiveData<Admin>
        get() = _admin

    fun login(id: String, pw: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val admin = Admin(0, id, pw, "")
                val response = repository.login(admin)
                if(response.isSuccessful){
                    _admin.postValue(response.body())
                    _isLoginSucceed.postValue(true)
                } else {
                    _isLoginSucceed.postValue(false)
                }
            } catch (e: Exception){
                Log.d(TAG, "login: ${e.message}")
            }
        }
    }
}