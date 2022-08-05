package com.d108.sduty.ui.main.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Member
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.model.dto.Study
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "StudyDetailViewModel"
class StudyDetailViewModel: ViewModel() {
    private val _myStudyInfo = MutableLiveData<Map<String, Any>>()
    val myStudyInfo: LiveData<Map<String, Any>>
        get() = _myStudyInfo

    private val _studyMasterNickname = MutableLiveData<Profile>()
    val studyMasterNickName: LiveData<Profile>
        get() = _studyMasterNickname

    fun getMyStudyInfo(userSeq: Int, studySeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 내 스터디 리스트 불러오기
                val response = Retrofit.studyApi.getMyStudyInfo(userSeq, studySeq)
                if(response.isSuccessful && response.body() != null){
                    _myStudyInfo.postValue(response.body() as Map<String, Any>)
                }

            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }

    fun masterNickname(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.profileApi.getProfileValue(userSeq)
                if(response.isSuccessful && response.body() != null){
                    _studyMasterNickname.postValue(response.body() as Profile)
                }
            } catch (e: Exception){
                Log.d(TAG, "masterNickname: ${e.message}")
            }
        }
    }


}