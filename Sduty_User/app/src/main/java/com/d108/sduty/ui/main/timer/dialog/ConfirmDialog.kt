package com.d108.sduty.ui.main.timer.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.databinding.DialogConfirmBinding
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel

class ConfirmDialog : DialogFragment() {
    private lateinit var binding: DialogConfirmBinding
    private val timerViewModel : TimerViewModel by viewModels({requireActivity()})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnConfirm.setOnClickListener {
                // DelayDialog 종료
                val fragment = requireActivity().supportFragmentManager.findFragmentByTag("DelayDialog")
                requireActivity().supportFragmentManager.beginTransaction().remove(fragment!!).commit()

                // Timer 측정 종료
                timerViewModel.stopTimer()

                // ConfirmDialog 종료
                dismiss()
            }
        }
    }
}