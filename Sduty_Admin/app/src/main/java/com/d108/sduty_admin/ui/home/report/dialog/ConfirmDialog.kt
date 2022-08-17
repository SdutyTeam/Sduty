package com.d108.sduty_admin.ui.home.report.dialog

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d108.sduty_admin.databinding.DialogConfirmBinding
import com.d108.sduty_admin.ui.MainViewModel
import com.d108.sduty_admin.util.getDeviceSize

class ConfirmDialog : DialogFragment() {
    private lateinit var binding: DialogConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogConfirmBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnRemove.setOnClickListener {
                onClickConfirmListener.onClick()
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }

    }

    lateinit var onClickConfirmListener: OnClickConfirmListener

    interface OnClickConfirmListener {
        fun onClick()
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = getDeviceSize(requireActivity()).x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}