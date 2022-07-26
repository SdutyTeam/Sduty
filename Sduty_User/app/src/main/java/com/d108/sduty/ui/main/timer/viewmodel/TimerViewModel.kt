package com.d108.sduty.ui.main.timer.viewmodel

import android.util.Log
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
import kotlin.concurrent.timer

private const val TAG = "TimerViewModel"
class TimerViewModel : ViewModel() {
    // 토스트 메시지
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
        get() = _toastMessage

    // 타이머
    private var time = 0
    private var timerTask : Timer? = null
    // 측정 중인 시간
    private val _timer = MutableLiveData<String>("00:00:00")
    val timer : LiveData<String>
        get() = _timer

    private val _isRunningTimer = MutableLiveData<Boolean>(false)
    val isRunningTimer : LiveData<Boolean>
        get() = _isRunningTimer

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

    fun useTimer(){
        when(time){
            0 -> startTimer()
            else -> stopTimer()
        }
    }

    private fun startTimer() {
        Log.d(TAG, "startTimer: ")
        _isRunningTimer.postValue(true)
        timerTask = timer(period = 1000){
            time++

            val hour = time / 60 / 60
            val min = time / 60
            val sec = time

            Log.d(TAG, "startTimer: ${time}")

            _timer.postValue("${setTimeFormat(hour)}:${setTimeFormat(min)}:${setTimeFormat(sec)}")
        }
    }

    private fun setTimeFormat(time: Int): String{
        return when(time < 10){
            true -> "0${time}"
            false -> time.toString()
        }
    }

    private fun stopTimer() {
        _isRunningTimer.postValue(false)
        timerTask?.cancel()

        time = 0
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