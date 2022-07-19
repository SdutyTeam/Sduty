package com.d108.sduty.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentMyPageBinding

// 마이페이지 - 내 닉네임, 프로필 사진, 숫자 표시(게시물, 팔로우, 팔로워), 한줄소개, 프로필 편집, 통계, 잔디그래프, 탭(내 게시물/ 스크랩), 내 게시물(그리드+스크롤) , 설정, 업적
private const val TAG ="MyPageFragment"
class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
