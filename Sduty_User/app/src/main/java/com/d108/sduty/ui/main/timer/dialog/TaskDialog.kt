package com.d108.sduty.ui.main.timer.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.databinding.DialogTaskBinding
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel
import com.d108.sduty.utils.convertTimeDateToString
import com.d108.sduty.utils.getDeviceSize
import java.util.*

class TaskDialog : DialogFragment() {
    private lateinit var binding : DialogTaskBinding
    private val timerViewModel: TimerViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val time = timerViewModel.timer.value!!
            val hour = time / 60 / 60
            val min = time / 60
            val sec = time % 60
            tvTimer.text = String.format("%02d:%02d:%02d",hour,min, sec)

            tvStartTime.setText(timerViewModel.startTime)
            tvEndTime.setText(convertTimeDateToString(Date(System.currentTimeMillis()),"hh:mm:ss"))

            btnDelete.setOnClickListener {
                ConfirmDialog().show(requireActivity().supportFragmentManager, "ConfirmDialog")
            }
        }

        timerViewModel.resetDelayTimer()
        timerViewModel.stopTimer()

    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes

        // 앱을 실행한 디바이스의 가로, 세로 크기를 가져온다.
        val deviceWidth = getDeviceSize(requireActivity()).x

        // 다이얼로그 크기를 디바이스 가로의 90%로 설정한다.
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}