package com.d108.sduty.ui.main.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudySearchBinding

// 스터디 검색 - 스터디 명, 카테고리 별 검색
private const val TAG = "StudySearchFragment"
class StudySearchFragment : Fragment() {
    private lateinit var binding: FragmentStudySearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}