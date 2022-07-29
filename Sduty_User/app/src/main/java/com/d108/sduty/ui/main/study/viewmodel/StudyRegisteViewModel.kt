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

private const val TAG = "StudyRegistViewModel"

class StudyRegisteViewModel: ViewModel() {
    private val _study = MutableLiveData<Study>()
    val study: LiveData<Study>
        get() = _study

    private val _createSuccess = MutableLiveData<Boolean>()
    val createSuccess: LiveData<Boolean>
        get() = _createSuccess

    private val _isStudyId = MutableLiveData<Boolean>()
    val isStudyId: LiveData<Boolean>
        get() = _isStudyId

    fun studyCreate(study: Study){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.studyApi.studyCreate(study)
                if(response.isSuccessful && response.body() != null){
                    _study.postValue((response.body() as Study))
                    _createSuccess.postValue(true)
                }
            }catch (e: Exception){
                Log.d(TAG, "studyCreate: ${e.message}")
                _createSuccess.postValue(false)
            }
        }
    }

    fun getStudyId(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.studyApi.getStudyName(id)
                if(response.isSuccessful && response.body() == true){
                    _isStudyId.postValue(true)
                    Log.d(TAG, "getStudyId: @@@")
                }
                else if(response.body() == false){
                    _isStudyId.postValue(false)
                }
            }catch (e: Exception){
                Log.d(TAG, "getStudyId: ${e.message}")
            }
        }
    }

}