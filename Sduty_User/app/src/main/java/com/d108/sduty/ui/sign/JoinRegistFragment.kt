package com.d108.sduty.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentJoinRegistBinding

//회원가입 - 정보입력 / 이름, 이메일, 아이디, 비밀번호, 비밀번호 확인, 휴대폰 번호, 인증
private const val TAG ="JoinRegistFragment"
class JoinRegistFragment : Fragment() {
    private lateinit var binding: FragmentJoinRegistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}