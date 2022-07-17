package com.d108.sduty.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentJoinBinding
import com.d108.sduty.model.dto.User
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.utils.movePage
import com.d108.sduty.utils.showToast

class JoinFragment : Fragment() {
    private lateinit var binding: FragmentJoinBinding
    private val signViewModel: SignViewModel by viewModels()
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
            btnJoin.setOnClickListener { signViewModel.join(User(editId.text.toString(), editPass.text.toString(), editName.text.toString(), editEmail.text.toString())) }
        }

        signViewModel.isJoinSucced.observe(viewLifecycleOwner){
            when (it) {
                true -> {
                    requireContext().showToast("회원가입이 완료되었습니다.")
                    (activity as MainActivity).movePage(R.id.frame_main, LoginFragment())
                }
                false -> { Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show() }
            }
        }
    }
}