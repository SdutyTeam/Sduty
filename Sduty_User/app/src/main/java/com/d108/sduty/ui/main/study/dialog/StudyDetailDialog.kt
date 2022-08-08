package com.d108.sduty.ui.main.study.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.d108.sduty.R
import com.d108.sduty.model.dto.Study

class StudyDetailDialog(context: Context, studyInfo: Study) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener
    private val studyInfo = studyInfo

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.dialog_study_detail)
        dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val studyJoinButton = dialog.findViewById<Button>(R.id.btn_study_join)
        val cancelButton = dialog.findViewById<Button>(R.id.btn_study_cancel)
        val studyName = dialog.findViewById<TextView>(R.id.study_name)
        val studyIntroduce = dialog.findViewById<TextView>(R.id.study_intoduce)

        studyName.text = studyInfo.name
        studyIntroduce.text = studyInfo.introduce

        studyJoinButton.setOnClickListener {
            onClickListener.onClicked()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }



    }

    interface OnDialogClickListener
    {
        fun onClicked()
    }
}