package com.d108.sduty.ui.main.mypage.setting

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.databinding.FragmentSettingBinding
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.mypage.setting.viewmodel.SettingViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.SettingsPreference
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showToast


private const val TAG = "SettingFragment"
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.apply {
            btnPushAll.setOnCheckedChangeListener { buttonView, isChecked ->
                when(isChecked){
                    true -> btnPushPersonal.isChecked = true
                    else -> {}
                }
            }
            btnPushPersonal.setOnCheckedChangeListener { buttonView, isChecked ->
                when(isChecked){
                    false -> btnPushAll.isChecked = false
                    else -> {}
                }
            }

            btnLogout.setOnClickListener {
                requireActivity().showAlertDialog("로그아웃","로그아웃 하시겠습니까?", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        clearActivity()
                    }
                })

            }
            btnLock.setOnClickListener {
                findNavController().safeNavigate(SettingFragmentDirections.actionSettingFragmentToAppLockFragment())
            }
            btnAutologin.isChecked = SettingsPreference().getAutoLoginState()
            btnAutologin.setOnCheckedChangeListener { buttonView, isChecked ->
                SettingsPreference().setAutoLoginState(isChecked)
                if(!isChecked){
                    SettingsPreference().setUserId("")
                }
            }

            btnResign.setOnClickListener{
                requireActivity().showAlertDialog("회원 탈퇴","정말로 탈퇴하시겠습니까?\n복구 할 수 없습니다.", object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when(which){
                            DialogInterface.BUTTON_POSITIVE -> {
                                viewModel.resignUser(mainViewModel.user.value!!)
                                clearActivity()
                            }
                        }
                    }
                })
            }

            btnAsk.setOnClickListener{
                requireContext().showToast("다음 업데이트에서 제공될 예정입니다.")
            }
            btnNotice.setOnClickListener {
                requireContext().showToast("다음 업데이트에서 제공될 예정입니다.")
            }
        }

        }

    private fun initViewModel(){
        viewModel.isSucceedResign.observe(viewLifecycleOwner){
            when(it){
                true -> {
                    requireActivity().showAlertDialog("회원 탈퇴", "탈퇴가 완료되었습니다.", null)
                    findNavController().safeNavigate(SettingFragmentDirections.actionSettingFragmentToLoginFragment())
                    clearActivity()
                }
                else -> {}
            }
        }
    }


    private fun clearActivity(){
        SettingsPreference().setUserId("")
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }



}