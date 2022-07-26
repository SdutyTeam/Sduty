package com.d108.sduty.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "StudyListViewModel"
class StudyListViewModel: ViewModel() {
    private val _list = MutableLiveData<List<String>>()
    val list: LiveData<List<String>>
        get() = _list

    fun getList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 전체 스터디 리스트 불러오기
            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }

}