package com.d108.sduty.ui.main.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Study
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "StudyListViewModel"
class StudyListViewModel: ViewModel() {
    private val _studyList = MutableLiveData<List<Study>>()
    val studyList: LiveData<List<Study>>
        get() = _studyList

    fun getStudyList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 전체 스터디 리스트 불러오기
                val response = Retrofit.studyApi.studyList()
                if(response.isSuccessful && response.body() != null){
                    _studyList.postValue(response.body() as List<Study>)
                }

            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }

}