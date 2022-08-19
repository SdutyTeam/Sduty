package com.d108.sduty_admin.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty_admin.databinding.FragmentLoginBinding
import com.d108.sduty_admin.ui.MainViewModel

private const val TAG ="LoginFragment"
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnLogin.setOnClickListener {
                val id = etId.text.toString()
                val pw = etPw.text.toString()

                if(id.isEmpty() || pw.isEmpty()){
                    Toast.makeText(context, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else{
                    loginViewModel.login(id, pw)
                }

            }
        }

        loginViewModel.isLoginSucceed.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: @")
            if(it){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else{
                Log.d(TAG, "onViewCreated: @@")
                Toast.makeText(context, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.admin.observe(viewLifecycleOwner) {
            if(it != null){
                viewModel.setAdminValue(it)
            }
        }

    }


}
