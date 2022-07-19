package com.d108.sduty.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentPostRegisterBinding

//게시물 등록 - 글 내용입력, 이미지 추가/ 미리보기, 카메라 or 이미지 선택, 태그 선택
private const val TAG ="PostRegisterFragment"
class PostRegisterFragment : Fragment() {
    private lateinit var binding: FragmentPostRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}