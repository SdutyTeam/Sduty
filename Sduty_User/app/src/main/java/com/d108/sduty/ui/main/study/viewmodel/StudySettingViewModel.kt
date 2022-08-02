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

private const val TAG = "StudySettingViewModel"
class StudySettingViewModel: ViewModel() {
    private val _isQuitStudy = MutableLiveData<Boolean>()
    val isQuitStudy: LiveData<Boolean>
        get() = _isQuitStudy

    private val _isDeleteStudy = MutableLiveData<Boolean>()
    val isDeleteStudy: LiveData<Boolean>
        get() = _isDeleteStudy


    // 스터디 탈퇴
    fun quitStudy(studySeq: Int, userSeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.studyApi.quitStudy(studySeq, userSeq)
                if(response.isSuccessful){
                    _isQuitStudy.postValue(true)
                }
                else if(response.code() == 500){
                    _isQuitStudy.postValue(false)
                }

            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }

    // 스터디 삭제
    fun deleteStudy(userSeq: Int, studySeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.studyApi.deleteStudy(userSeq, studySeq)
                if(response.isSuccessful){
                    _isDeleteStudy.postValue(true)
                }
                else if(response.code() == 500){
                    _isDeleteStudy.postValue(false)
                }

            } catch (e: Exception){
                Log.d(TAG, "deleteStudy: ${e.message}")
            }
        }

    }




}