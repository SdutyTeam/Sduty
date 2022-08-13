package com.d108.sduty.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.d108.sduty.R
import com.d108.sduty.common.FLAG_CAMERA
import com.d108.sduty.common.FLAG_GALLERY
import com.d108.sduty.databinding.DialogCropSelectBinding

private const val TAG ="CropSelectDialog"
class CropSelectDialog : DialogFragment() {
    private lateinit var binding: DialogCropSelectBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCropSelectBinding.inflate(inflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDialog()

        binding.apply {
            cardCamera.setOnClickListener {
                onClickListener.onClick(FLAG_CAMERA)
                dismiss()
            }
            cardGallery.setOnClickListener {
                onClickListener.onClick(FLAG_GALLERY)
                dismiss()
            }
        }
    }

    lateinit var onClickListener: OnClickListener
    interface OnClickListener{
        fun onClick(flag: Int)
    }

    private fun showDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // TRANSPARENT?

        dialog?.window?.attributes?.windowAnimations = R.style.RegisterDialogAnimation
        dialog?.window?.setGravity(Gravity.BOTTOM)

        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(true)
        dialog?.show()

    }
}
