package com.d108.sduty.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentProfileRegistBinding
import com.d108.sduty.ui.sign.viewmodel.ProfileViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel

//프로필 등록 - 프로필 사진, 별명, 직업, 관심 분야, 생년월일, 자기소개
private const val TAG = "ProfileRegistFragment"
class ProfileRegistFragment : Fragment() {
    private lateinit var binding: FragmentProfileRegistBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initViewModel(){

    }

    private fun initView(){
        binding.apply {
            lifecycleOwner = this@ProfileRegistFragment
            vm = viewModel
            btnSave.setOnClickListener {
                saveProfile()
            }
            etNickname.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.checkNickname(binding.etNickname.text.toString())
                }
            })
        }
    }

    private fun saveProfile(){
        binding.apply{
            val nickname = etNickname.text.toString()
            val birth = etBirth.text.toString()
            val publicBirth = viewModel.profile.value!!.profile_public_birth
        }
    }
}
