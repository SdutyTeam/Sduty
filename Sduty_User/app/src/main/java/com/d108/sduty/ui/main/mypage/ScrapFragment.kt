package com.d108.sduty.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentScrapBinding

//@ 내 게시물/ 스크랩 탭레이아웃 아래 프래그먼트로 구성? 마이 페이지 리사이클러 뷰 재사용?
private const val TAG = "ScrapFragment"
class ScrapFragment : Fragment() {
    private lateinit var binding: FragmentScrapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScrapBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}