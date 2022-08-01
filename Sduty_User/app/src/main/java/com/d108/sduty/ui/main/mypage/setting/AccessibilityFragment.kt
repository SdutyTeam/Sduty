package com.d108.sduty.ui.main.mypage.setting

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentAccessibilityBinding

private const val TAG = "AccessibilityFragment"
class AccessibilityFragment : Fragment() {
    private lateinit var binding: FragmentAccessibilityBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccessibilityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun checkAccessibilityPermissions(): Boolean{
        val accessibilityManager = requireContext().getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
        Log.d(TAG, "checkAccessibilityPermissions: size: ${list.size}")
        for(i in 0 until list.size){
            val info = list[i]
            return info.resolveInfo.serviceInfo.packageName.equals(requireActivity().application.packageName)
        }
        return false
    }

    private fun setAccessibilityPermissions(){
        val permissionDialog = AlertDialog.Builder(requireContext())
        permissionDialog.apply {
            setTitle("접근성 권한 설정")
            setMessage("앱을 사용하기 위해 접근성 권한이 필요합니다.")
            setPositiveButton("허용", object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    requireContext().startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                }
            }).create().show()
        }
    }
}