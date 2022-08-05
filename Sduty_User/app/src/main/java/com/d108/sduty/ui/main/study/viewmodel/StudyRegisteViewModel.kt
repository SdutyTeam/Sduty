package com.d108.sduty.ui.main.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Alarm
import com.d108.sduty.model.dto.Study
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.log

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
                Log.d(TAG, "studyCreate: ${mapOf("Study" to study)}")
                Retrofit.studyApi.studyCreate(mapOf("Study" to study)).let {
                    if(it.isSuccessful){
                        _createSuccess.postValue(true)
                    }
                    else if (it.code() == 500) {
                        _createSuccess.postValue(false)
                    }
                }
            } catch (e: Exception){
                Log.d(TAG, "error: ${e.message}")
            }
        }
    }

    fun camStudyCreate(study: Study, alarm: Alarm){
        viewModelScope.launch(Dispatchers.IO) {
            val studyObj: Map<String, Objects> = mapOf("Study" to study, "Alarm" to alarm) as Map<String, Objects>
            Log.d(TAG, "camStudyCreate: ${studyObj}")
            try {
                Retrofit.studyApi.camStudyCreate(studyObj).let {
                    Log.d(TAG, "camStudyCreate: ${it}")
                    if(it.isSuccessful){
                        _createSuccess.postValue(true)
                    }
                    else if (it.code() == 500) {
                        _createSuccess.postValue(false)
                    }
                }
            } catch (e: Exception){
                Log.d(TAG, "error: ${e.message}")
            }

        }
    }

    fun getStudyId(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Retrofit.studyApi.getStudyName(id)
                if(response.code() == 200){
                    _isStudyId.postValue(false)
                }
                else if(response.code() == 401){
                    _isStudyId.postValue(true)
                }
            }catch (e: Exception){
                Log.d(TAG, "getStudyId: ${e.message}")
            }
        }
    }

}