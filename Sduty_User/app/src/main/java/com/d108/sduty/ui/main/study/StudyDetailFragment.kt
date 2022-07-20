package com.d108.sduty.ui.main.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyDetailBinding

// 스터디 상세 -
private const val TAG = "StudyDetailFragment"
class StudyDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudyDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}