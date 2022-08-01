package com.d108.sduty.ui.main.mypage.setting

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentSettingBinding
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog


private const val TAG = "SettingFragment"
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
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
    }

    private fun initView() {
        binding.apply {
            btnLogout.setOnClickListener {
                requireActivity().showAlertDialog("로그아웃","로그아웃 하시겠습니까?", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        findNavController().safeNavigate(SettingFragmentDirections.actionSettingFragmentToLoginFragment())
                    }
                })

            }
            btnLock.setOnClickListener {
                findNavController().safeNavigate(SettingFragmentDirections.actionSettingFragmentToAccessibilityFragment())
            }
        }
    }


}