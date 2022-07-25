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
    // 사용자가 선택한 날짜
    private val _selectedDate = MutableLiveData<Date>()
    val selectedDate : LiveData<Date>
        get() = _selectedDate

    // TODO: Report DTO 작성 필요
    // 선택된 날짜의 리포트 목록
    private val _reportList = MutableLiveData<List<Report>>()
    val reportList : LiveData<List<Report>>
        get() = _reportList

    // TODO: userSeq 입력 필요
    // 날짜 선택
    fun selectDate(strDate: String){
        // 날짜를 Date 타입으로 변환
        val selectedDate = convertTimeStringToDate(strDate, "yyyy년 M월 d일")
        getReportList(0, selectedDate)
    }

    // TODO: API 작성 필요
    // 선택된 날짜의 리포트 목록을 요청
    private fun getReportList(userSeq : Int, selectedDate : Date){
        CoroutineScope(Dispatchers.IO).launch{
            Retrofit.timerApi.getReportList(userSeq, selectedDate).let{
                // 사용자 리포트
                if(it.isSuccessful && it.body() != null){

                } else {
                    // 못 받았을 때
                }
            }
        }
    }

}