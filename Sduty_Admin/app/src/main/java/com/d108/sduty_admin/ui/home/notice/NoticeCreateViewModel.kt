package com.d108.sduty_admin.ui.home.notice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty_admin.model.dto.Notice
import com.d108.sduty_admin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "NoticeViewModel"
class NoticeCreateViewModel: ViewModel() {
    private val repository = Repository.get()

    private val _createSuccess = MutableLiveData<Boolean>()
    val createSuccess: LiveData<Boolean>
        get() = _createSuccess

    fun createNotice(notice: Notice){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.insertNotice(notice)
                if(response.isSuccessful){
                    _createSuccess.postValue(true)
                } else if(response.code() == 500){
                    _createSuccess.postValue(false)
                }
            } catch (e: Exception){
                Log.d(TAG, "createNotice: ${e.message}")
            }
        }
    }
}