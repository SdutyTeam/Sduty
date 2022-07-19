package com.d108.sduty.ui.main.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyRegistBinding

// 스터디 등록 - 스터디 명, 공개 설정, 비밀번호 설정, 최대 인원, 카테고리, 스터디 설명, 일반/캠스터디 설정
private const val TAG ="StudyRegistFragment"
class StudyRegistFragment : Fragment() {
    private lateinit var binding: FragmentStudyRegistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}