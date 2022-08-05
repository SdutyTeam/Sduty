package com.d108.sduty.ui.main.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Member
import com.d108.sduty.model.dto.Study
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "StudyDetailViewModel"
class StudyDetailViewModel: ViewModel() {
    private val _myStudyInfo = MutableLiveData<Map<String, Any>>()
    val myStudyInfo: LiveData<Map<String, Any>>
        get() = _myStudyInfo

    private val _memberList = MutableLiveData<List<Member>>()
    val memberList: LiveData<List<Member>>
        get() = _memberList


    fun getMyStudyInfo(userSeq: Int, studySeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 내 스터디 리스트 불러오기
                val response = Retrofit.studyApi.getMyStudyInfo(userSeq, studySeq)
                Log.d(TAG, "getMyStudyInfo1: ${response}")
                Log.d(TAG, "getMyStudyInfo2: ${response.body()}")
                if(response.isSuccessful && response.body() != null){
                    val obj = response.body() as Map<String, Any>
                    //Log.d(TAG, "getMyStudyInfo: ${obj["study"] as Study}")
                    Log.d(TAG, "getMyStudyInfo: ${obj["members"] as List<Member>}")
//                    val meee = obj["members"] as List<Member>
//                    meee[0].is_studying
                    //_memberList.postValue(listOf(response.body() as Member))
                }

            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }


}