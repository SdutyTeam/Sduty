package com.d108.sduty.ui.main.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.model.dto.Study
import com.d108.sduty.model.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MyStudyViewModel"
class MyStudyViewModel: ViewModel() {
    private val _myStudyList = MutableLiveData<List<Study>>()
    val myStudyList: LiveData<List<Study>>
        get() = _myStudyList


    fun getMyStudyList(profile: Profile){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 내 스터디 리스트 불러오기
                val response = Retrofit.studyApi.myStudyList(profile.profile_user_seq)
                if(response.isSuccessful && response.body() != null){
                    _myStudyList.postValue(response.body() as List<Study>)
                }

            }catch (e: Exception){
                Log.d(TAG, "getList: ${e.message}")
            }
        }
    }

}