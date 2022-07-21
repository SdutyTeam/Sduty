package com.d108.sduty.ui.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.d108.sduty.common.COMMON_JOIN
import com.d108.sduty.common.KAKAO_JOIN
import com.d108.sduty.common.NAVER_JOIN
import com.d108.sduty.databinding.FragmentJoinBinding
import com.d108.sduty.utils.safeNavigate

//회원가입 페이지 / 이메일 아이디로 가입, 카카오로 가입, 네이버로 가입
private const val TAG ="JoinFragment"
class JoinFragment : Fragment() {
    private lateinit var binding: FragmentJoinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnJoinId.setOnClickListener {
                findNavController().safeNavigate(JoinFragmentDirections.actionJoinFragmentToTermsFragment(COMMON_JOIN))
            }
            btnJoinKakao.setOnClickListener {
                findNavController().safeNavigate(JoinFragmentDirections.actionJoinFragmentToTermsFragment(KAKAO_JOIN))
            }
            btnJoinNaver.setOnClickListener {
                findNavController().safeNavigate(JoinFragmentDirections.actionJoinFragmentToTermsFragment(NAVER_JOIN))
            }
        }
    }
}