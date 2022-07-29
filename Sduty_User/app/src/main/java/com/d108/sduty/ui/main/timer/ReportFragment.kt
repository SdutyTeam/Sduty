package com.d108.sduty.ui.main.timer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentReportBinding
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate

class ReportFragment : Fragment() {
    private lateinit var binding : FragmentReportBinding

    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // 리포트로 이동
            fabReport.setOnClickListener {
                findNavController().safeNavigate(ReportFragmentDirections.actionReportFragmentToTimerFragment())
            }
        }

    }
}