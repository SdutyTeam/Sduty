package com.d108.sduty.ui.main.timer.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.d108.sduty.R
import com.d108.sduty.databinding.DialogTaskBinding

class TaskDialog : DialogFragment() {
    private lateinit var binding : DialogTaskBinding

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
            btnDelete.setOnClickListener {
                ConfirmDialog().show(requireActivity().supportFragmentManager, "ConfirmDialog")
            }
        }

    }
}