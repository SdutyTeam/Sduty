package com.d108.sduty_admin.ui.home.qna

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.d108.sduty_admin.databinding.DialogQuestionDetailBinding
import com.d108.sduty_admin.model.dto.Qna
import com.d108.sduty_admin.util.getDeviceSize

class QuestionDetailDialog(var qna: Qna) : DialogFragment() {
    private lateinit var binding: DialogQuestionDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogQuestionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            data = qna
            btnCancel.setOnClickListener {
                qna.answer = etAnswer.text.toString()
                onClickQuestionDetailListener.onClick(qna)
                dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes

        // 앱을 실행한 디바이스의 가로, 세로 크기를 가져온다.
        val deviceWidth = getDeviceSize(requireActivity()).x

        // 다이얼로그 크기를 디바이스 가로의 90%로 설정한다.
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    lateinit var onClickQuestionDetailListener: OnClickQuestionDetailListener
    interface OnClickQuestionDetailListener{
        fun onClick(qna: Qna)
    }
}
