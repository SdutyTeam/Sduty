package com.d108.sduty.ui.main.timer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Report
import com.d108.sduty.utils.convertTimeStringToDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TimerViewModel"
class TimerViewModel : ViewModel() {
    // 토스트 메시지
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
        get() = _toastMessage

    // 사용자가 선택한 날짜
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate : LiveData<String>
        get() = _selectedDate

    // 선택된 날짜의 리포트
    private val _report = MutableLiveData<Report>()
    val report : LiveData<Report>
        get() = _report

    // TODO: userSeq 입력 필요
    // 사용자가 날짜 선택
    fun selectDate(strDate: String){
        val selectedDate = convertTimeStringToDate(strDate, "yyyy년 M월 d일")
        getReportOfSelectedDate(0, selectedDate)
    }

    /* API */

    // 선택된 날짜의 리포트를 요청
    private fun getReportOfSelectedDate(userSeq : Int, selectedDate : Date){
        CoroutineScope(Dispatchers.IO).launch{
            Retrofit.timerApi.getReport(userSeq, selectedDate).let{
                // 사용자 리포트
                if(it.isSuccessful && it.body() != null){
                    _report.postValue(it.body())
                } else {
                    // 못 받았을 때
                    _toastMessage.postValue("서버에서 리포트를 받아오는데 실패했습니다.")
                }
            }
        }
    }

}