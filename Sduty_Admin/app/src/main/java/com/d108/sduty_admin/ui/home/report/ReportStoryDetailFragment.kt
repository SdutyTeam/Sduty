package com.d108.sduty_admin.ui.home.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty_admin.R
import com.d108.sduty_admin.databinding.FragmentReportStoryDetailBinding

private const val TAG ="ReportStoryDetailFragment"
class ReportStoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentReportStoryDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportStoryDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
