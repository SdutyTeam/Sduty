package com.d108.sduty.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentUserProfileBinding

//사용자 프로필 - 사용자 닉네임, 프로필 사진, 숫자 표시(게시물, 팔로우, 팔로워), 한줄소개,
// 잔디그래프,게시물(그리드+스크롤) , 더 보기, 업적
private const val TAG = "UserProfileFragment"
class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
