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
class NoticeViewModel: ViewModel() {
    private val repository = Repository.get()

    private val _noticeList = MutableLiveData<List<Notice>>()
    val noticeList: LiveData<List<Notice>>
        get() = _noticeList

    fun getNoticeList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNoticeList()
                if(response.isSuccessful && response.body() != null){
                    _noticeList.postValue(response.body() as List<Notice>)
                }
            } catch (e: Exception){
                Log.d(TAG, "getNoticeList: ${e.message}")
            }
        }

    }

}