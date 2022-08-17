package com.d108.sduty.ui.main.mypage.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d108.sduty.databinding.FragmentQuestionBinding

// 1:1 문의 - 문의 내역 조회, 1:1 문의 등록
private const val TAG ="QuestionFragment"
class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}