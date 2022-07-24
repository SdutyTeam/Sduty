package com.d108.sduty.ui.sign

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.common.COMMON_JOIN
import com.d108.sduty.common.KAKAO_JOIN
import com.d108.sduty.common.NAVER_JOIN
import com.d108.sduty.databinding.FragmentTermsBinding

// 회원가입 약관 - 동의 시 가입화면 이동
private const val TAG = "TermsFragment"
class TermsFragment : Fragment() {
    private lateinit var binding: FragmentTermsBinding
    private val args: TermsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            btnAccept.setOnClickListener {
                // todo: 약관 동의 체크
                when(args.route){
                    COMMON_JOIN -> findNavController().navigate(TermsFragmentDirections.actionTermsFragmentToJoinRegistFragment(args.route))
                    KAKAO_JOIN -> findNavController().navigate(TermsFragmentDirections.actionTermsFragmentToJoinRegistFragment(args.route, args.token))
                    NAVER_JOIN -> findNavController().navigate(TermsFragmentDirections.actionTermsFragmentToJoinRegistFragment(args.route, args.token))
                }

            }
        }
    }

}