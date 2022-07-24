package com.d108.sduty.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.common.COMMON_JOIN
import com.d108.sduty.databinding.FragmentJoinRegistBinding
import com.d108.sduty.model.dto.User
import com.d108.sduty.ui.sign.viewmodel.JoinViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showToast
import java.util.regex.Pattern

//회원가입 - 정보입력 / 이름, 이메일, 아이디, 비밀번호, 비밀번호 확인, 휴대폰 번호, 인증
private const val TAG ="JoinRegistFragment"
class JoinRegistFragment : Fragment() {
    private lateinit var binding: FragmentJoinRegistBinding
    private val viewModel: JoinViewModel by viewModels()
    private val args: JoinRegistFragmentArgs by navArgs()

    private val mailList = listOf("gmail.com", "naver.com", "daum.net", "직접 입력")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinRegistBinding.inflate(inflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initViewModel() {
        viewModel.apply {
            setJoinFlag(args.route)
            isJoinSucced.observe(viewLifecycleOwner){
                when(it){
                    true -> {
                        requireContext().showToast("회원 가입이 완료되었습니다.")
                        findNavController().safeNavigate(JoinRegistFragmentDirections.actionJoinRegistFragmentToLoginFragment())
                    }
                }
            }
        }

    }

    private fun initView() {
        binding.apply {
            lifecycleOwner = this@JoinRegistFragment
            vm = viewModel
            btnSendSms.setOnClickListener {
                val tel = "${etPhoneFront.text}+${etPhoneEnd.text}"
                viewModel.sendOTP(tel)
            }
            btnAuthCode.setOnClickListener {
                viewModel.checkOTP(etAuthCode.text.toString())
            }
            btnJoin.setOnClickListener {
                checkJoinForm()
            }
            etPw.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if(s != null){
                        viewModel.checkPWLength(binding.etPw.text.toString())
                    }
                }
            })
            etPwConfirm.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.checkPW(etPw.text.toString(), etPwConfirm.text.toString())
                }
            })
            etId.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.getUsedId(binding.etId.text.toString())
                }
            })
            spinnerEmail.apply {
                adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mailList)
                onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if(position == mailList.size - 1){
                            viewModel.setSelfInput()
                        }
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
            var filter = arrayOf(InputFilter{src, start, end, dst, dstart, dend ->
                val ps = Pattern.compile("^[a-zA-Z0-9]+\$")
                if(!ps.matcher(src).matches()){
                    return@InputFilter ""
                }else{
                    return@InputFilter null
                }
            }, InputFilter.LengthFilter(15))
            etId.filters = filter

            filter = arrayOf(InputFilter{src, start, end, dst, dstart, dend ->
                val ps = Pattern.compile("^[a-zA-Z0-9!@#$%^&*]+\$")
                if(!ps.matcher(src).matches()){
                    return@InputFilter ""
                }else{
                    return@InputFilter null
                }
            }, InputFilter.LengthFilter(20))

            etPw.filters = filter
            etPwConfirm.filters = filter

        }
    }

    private fun checkJoinForm(){
        binding.apply {
            val id = etId.text.toString()
            val pw = etPw.text.toString()
            val name = etName.text.toString()
            val tel = "${etPhoneFront.text}${etPhoneEnd.text}"
            var email = ""
            if(spinnerEmail.selectedItemPosition == mailList.size - 1){
                email = "${etEmail.text}@${etEmailEnd.text}"
            }else{
                email = "${etEmail.text}@${spinnerEmail.selectedItem}"
            }
            Log.d(TAG, "checkJoinForm: $email")
            Log.d(TAG, "checkJoinForm: $tel")

            when(args.route) {
                COMMON_JOIN -> {

                    if (
                        viewModel.isSucceedAuth.value as Boolean){

                    }else{
                        requireContext().showToast("모든 항목을 입력해 주세요")
                    }

                }
            }
        }
    }

}