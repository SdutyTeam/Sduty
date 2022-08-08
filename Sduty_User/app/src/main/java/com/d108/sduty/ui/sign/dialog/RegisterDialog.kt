package com.d108.sduty.ui.sign.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.d108.sduty.R

class RegisterDialog (context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_register)

        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE)) // TRANSPARENT?

        dialog.window?.attributes?.windowAnimations = R.style.RegisterDialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        // TODO : 다이얼로그 버튼 동작 정의
    }

    interface OnDialogClickListener{
        fun onClicked(type: Boolean)
    }
}