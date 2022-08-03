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

private const val TAG = "StudySearchViewModel"
class StudySearchViewModel: ViewModel() {
    private val _studySearchList = MutableLiveData<List<Study>>()
    val studySearchList: LiveData<List<Study>>
        get() = _studySearchList

    fun getStudySearchList(keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 내 스터디 리스트 불러오기
                val response = Retrofit.studyApi.studySearch(keyword)
                Log.d(TAG, "getStudySearchList: ${response}")
                if(response.isSuccessful && response.body() != null){
                    _studySearchList.postValue(response.body() as List<Study>)
                }
            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }
}