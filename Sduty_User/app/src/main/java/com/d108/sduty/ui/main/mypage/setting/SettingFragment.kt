package com.d108.sduty.ui.main.mypage.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentSettingBinding
import com.d108.sduty.utils.safeNavigate


private const val TAG = "SettingFragment"
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
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
            btnLock.setOnClickListener {
                findNavController().safeNavigate(SettingFragmentDirections.actionSettingFragmentToAccessibilityFragment())
            }
        }
    }


}