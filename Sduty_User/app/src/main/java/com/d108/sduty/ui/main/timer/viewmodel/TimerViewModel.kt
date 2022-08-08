package com.d108.sduty.ui.main.timer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d108.sduty.common.ApplicationClass
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Report
import com.d108.sduty.model.dto.Task
import com.d108.sduty.model.dto.User
import com.d108.sduty.utils.convertTimeDateToString
import com.d108.sduty.utils.convertTimeStringToDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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

    // 현재 동작 중인지 검사
    private val _isRunningTimer = MutableLiveData<Boolean>(false)
    val isRunningTimer: LiveData<Boolean>
        get() = _isRunningTimer

    // 현재 유예 중인지 검사
    private var isDelayingTimer = false

    // 시간 측정 시작 시간
    private var _startTime: String = "00:00:00"
    val startTime: String
        get() = _startTime

    // 사용자가 날짜 선택
    fun selectDate(strDate: String) {
        val selectedDate = convertTimeStringToDate(strDate, "yyyy년 M월 d일")
        val convertedDate = convertTimeDateToString(selectedDate, "yyyy-MM-dd")
        val userSeq = ApplicationClass.userPref.getInt("seq", 47)
        getReport(userSeq, convertedDate)
    }

    // 앱이 종료 되었을 경우 측정 시간을 복구한다.
    fun restoreTime() {
        val savedStartTime = ApplicationClass.timerPref.getString("StartTime", "")!!

        // 측정하던 정보가 남아 있을 경우
        if (savedStartTime.isNotEmpty() && !isRunningTimer.value!!) {
            val convertedStartTime =
                convertTimeStringToDate(savedStartTime!!, "yyyy-MM-dd hh:mm:ss")
            // 공부한 시간
            val studyTime = System.currentTimeMillis() - convertedStartTime.time

            _startTime = convertTimeDateToString(convertedStartTime, "hh:mm:ss")

            _timer.value = (studyTime / 1000).toInt()

            startTimer()
        }
    }

    // 시간 측정을 시작한다.
    fun startTimer() {
        // TODO: 스터디에 시작 정보 알림

        _isRunningTimer.value = true

        timerTask = timer(period = 1000) {
            _timer.postValue(timer.value!! + 1)
            if (isDelayingTimer) {
                _delayTime.postValue(delayTime.value!! + 1)
            }
        }
    }

    // 측정 시작 시간을 저장한다.
    fun saveTime() {
        _startTime = convertTimeDateToString(Date(System.currentTimeMillis()), "hh:mm:ss")

        // 시작 시간을 디바이스에 저장
        ApplicationClass.timerPref.edit().putString(
            "StartTime",
            convertTimeDateToString(Date(System.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
        ).apply()
    }

    // 측정 시간을 유예한다.
    fun delayTimer() {
        isDelayingTimer = true
    }

    // 측정을 계속 한다.
    fun resetDelayTimer() {
        isDelayingTimer = false
        _delayTime.value = 0
    }

    // 시간 측정을 종료한다.
    fun stopTimer() {

        // TODO: 스터디에 종료 정보 알림

        ApplicationClass.timerPref.edit().putString("StartTime", "").apply()
        _isRunningTimer.postValue(false)
        timerTask?.cancel()
        _timer.postValue(0)
        resetDelayTimer()
    }

    fun saveTask(report: Report) {
        // Task 저장
        insertTask(report)
    }

    fun removeTask(position: Int) {
        val seq = report.value!!.tasks[position].seq
        deleteTask(seq)
    }

    fun modifyTask(task: Task){
        updateTask(task)
    }

    fun getTodayReport(userSeq: Int){
        val today = convertTimeDateToString(Date(System.currentTimeMillis()), "yyyy-MM-dd")
        getReport(userSeq, today)
    }

    /* API */

    // 선택된 날짜의 리포트를 요청
    private fun getReport(userSeq: Int, selectedDate: String) {
        Log.d(TAG, "getReport: $userSeq $selectedDate")
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit.timerApi.getReport(userSeq, selectedDate).let {
                // 사용자 리포트
                if (it.isSuccessful && it.body() != null) {
                    Log.d(TAG, "getReport: body ${it.body()}")
                    _report.postValue(it.body())
                } else if (it.code() == 401) {
                    // 못 받았을 때
                    Log.d(TAG, "getReport: error ${it.errorBody()}")
                    _report.postValue(Report(0, 0, selectedDate, "00:00:00", listOf()))
                } else {
                    _toastMessage.postValue("서버에서 불러오는데 실패했습니다..")
                }
            }
        }
    }

    // 시간 측정 기록 저장
    private fun insertTask(report: Report) {
        Log.d(TAG, "insertTask: ${report}")
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit.timerApi.insertTask(report).let {
                if (it.isSuccessful) {
                    _toastMessage.postValue("측정 기록 등록을 완료하였습니다.")
                } else {
                    Log.e(TAG, "saveTask: ${it.code()}")
                    _toastMessage.postValue("서버에 등록하는데 실패하였습니다.")
                }
            }
        }
    }

    // 삭제
    private fun deleteTask(seq: Int) {
        Log.d(TAG, "deleteTask: ${seq}")
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit.timerApi.deleteTask(seq).let {
                if (it.isSuccessful) {
                    _toastMessage.postValue("측정 기록 삭제를 완료하였습니다.")
                } else {
                    Log.e(TAG, "saveTask: ${it.code()}")
                    _toastMessage.postValue("서버에 요청하는데 실패하였습니다.")
                }
            }
        }
    }

    // 수정
    private fun updateTask(task: Task){
        Log.d(TAG, "updateTask: ${task}")
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit.timerApi.updateTask(task.seq, task).let {
                if (it.isSuccessful) {
                    _toastMessage.postValue("측정 기록 삭제를 완료하였습니다.")
                } else {
                    Log.e(TAG, "saveTask: ${it.code()}")
                    _toastMessage.postValue("서버에 요청하는데 실패하였습니다.")
                }
            }
        }
    }

}