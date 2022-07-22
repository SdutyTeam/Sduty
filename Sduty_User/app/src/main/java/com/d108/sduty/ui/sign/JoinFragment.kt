package com.d108.sduty.ui.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.common.COMMON_JOIN
import com.d108.sduty.common.KAKAO_JOIN
import com.d108.sduty.common.NAVER_JOIN
import com.d108.sduty.databinding.FragmentJoinBinding
import com.d108.sduty.model.dto.User
import com.d108.sduty.ui.sign.viewmodel.JoinViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showToast

//회원가입 페이지 / 이메일 아이디로 가입, 카카오로 가입, 네이버로 가입
private const val TAG ="JoinFragment"
class JoinFragment : Fragment() {
    private lateinit var binding: FragmentJoinBinding
    private val viewModel: JoinViewModel by viewModels()
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

    private fun initViewModel(){
        viewModel.isJoinSucced.observe(viewLifecycleOwner){
            when(it) {
                true -> requireContext().showToast("가입이 완료되었습니다. 로그인해 주세요")
            }
        }
    }
}