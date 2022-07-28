package com.d108.sduty.ui.main.timer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Report
import com.d108.sduty.utils.convertTimeDateToString
import com.d108.sduty.utils.convertTimeStringToDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer

private const val TAG = "TimerViewModel"

class TimerViewModel() : ViewModel() {
    // 토스트 메시지
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    // 선택된 날짜의 리포트
    private val _report = MutableLiveData<Report>()
    val report: LiveData<Report>
        get() = _report

    // 측정 중인 시간
    private val _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int>
        get() = _timer
    private var timerTask: Timer? = null

    // 유예 시간
    private val _delayTime = MutableLiveData<Int>(0)
    val delayTime: LiveData<Int>
        get() = _delayTime
    private var delayTask: Timer? = null

    private val _isRunningTimer = MutableLiveData<Boolean>(false)
    val isRunningTimer: LiveData<Boolean>
        get() = _isRunningTimer

    // 시간 측정 시작 시간
    private var startTime: String = "00:00:00"

    // TODO: userSeq 입력 필요
    // 사용자가 날짜 선택
    fun selectDate(strDate: String) {
        val selectedDate = convertTimeStringToDate(strDate, "yyyy년 M월 d일")
        Log.d(TAG, "selectDate: ${selectedDate}")
        getReport(1, selectedDate)
    }

    // TODO: SharedPreferences 또는 SaveStateViewModel 
    // 앱이 종료 되었을 경우 측정 시간을 복구한다.
    private fun restoreTime() {

    }

    private fun saveTime(startTime: String) {

    }

    // 시간 측정을 시작한다.
    fun startTimer() {
        // 측정 시작 시간을 저장한다.
        startTime = convertTimeDateToString(Date(System.currentTimeMillis()), "hh:mm:ss")
        saveTime(startTime)

        // TODO: 스터디에 시작 정보 알림

        _isRunningTimer.value = true

        timerTask = timer(period = 1000) {
            Log.d(TAG, "startTimer: ${timer.value}")
            Log.d(TAG, "timerTask: ${_isRunningTimer.value}")

            _timer.postValue(timer.value!! +1)
        }
    }

    // 측정 시간을 유예한다.
    fun delayTimer() {
        delayTask = timer(period = 1000) {

            when(delayTime.value){
                20 -> stopTimer()
                else -> _delayTime.postValue(delayTime.value!! +1)
            }
        }
    }

    // 측정을 계속 한다.
    fun continueTimer() {
        delayTask?.cancel()
        _delayTime.value = 0
    }

    // 시간 측정을 종료한다.
    fun stopTimer() {

        // TODO: 스터디에 종료 정보 알림

        _isRunningTimer.postValue(false)
        timerTask?.cancel()
        delayTask?.cancel()
        _timer.postValue(0)
    }

    /* API */

    // 선택된 날짜의 리포트를 요청
    private fun getReport(userSeq: Int, selectedDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit.timerApi.getReport(userSeq, selectedDate).let {
                // 사용자 리포트
                if (it.isSuccessful && it.body() != null) {
                    _report.postValue(it.body())
                    Log.d(TAG, "getReport: ${it.body()}")
                } else {
                    // 못 받았을 때
                    Log.d(TAG, "getReport: ${it.errorBody()}")
                    _toastMessage.postValue("서버에서 리포트를 받아오는데 실패했습니다.")
                }
            }
        }
    }

}