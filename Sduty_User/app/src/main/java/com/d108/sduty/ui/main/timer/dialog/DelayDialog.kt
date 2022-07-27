package com.d108.sduty.ui.main.timer.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.d108.sduty.databinding.DialogDelayBinding
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel


private const val TAG = "ReportDialog"

class DelayDialog : DialogFragment() {
    private lateinit var binding: DialogDelayBinding

    private val timerViewModel: TimerViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDelayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        binding.apply {
            timerViewModel.timer.observe(viewLifecycleOwner) { time ->
                val hour = time / 60 / 60
                val min = time / 60
                val sec = time % 60
                tvTimer.text = String.format("%02d:%02d:%02d",hour,min, sec)
            }

            timerViewModel.delayTime.observe(viewLifecycleOwner) { delayTime ->
                // 20초가 경과하면 종료
                if(delayTime == 20){
                    TaskDialog().show(requireActivity().supportFragmentManager, "TaskDialog")

                    dismiss()
                }
                tvCountdown.text = "측정을 이어서 하려면 \n[${20 - delayTime}]초 이내에 클릭하세요"
            }

            btnContinue.setOnClickListener {
                timerViewModel.continueTimer()
                dismiss()
            }
            btnFinish.setOnClickListener {
                TaskDialog().show(requireActivity().supportFragmentManager, "TaskDialog")
                dismiss()
            }
        }
    }
}
