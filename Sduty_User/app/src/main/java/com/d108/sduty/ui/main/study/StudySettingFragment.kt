package com.d108.sduty.ui.main.study

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudySettingBinding
import com.d108.sduty.ui.main.study.viewmodel.StudySearchViewModel
import com.d108.sduty.ui.main.study.viewmodel.StudySettingViewModel
import com.d108.sduty.utils.showAlertDialog


class StudySettingFragment : Fragment() {
    private lateinit var binding: FragmentStudySettingBinding
    private val studySettingViewModel: StudySettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudySettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnStudyQuit.setOnClickListener {
                requireActivity().showAlertDialog("그룹 탈퇴", "정말로 그룹을 탈퇴하시겠습니까?"
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            //studySettingViewModel.quitStudy()
                        }
                    }
                }
            }

            btnStudyDelete.setOnClickListener {
                requireActivity().showAlertDialog("그룹 삭제", "정말로 그룹을 삭제하시겠습니까?"
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            //studySettingViewModel.deleteStudy()
                        }
                    }
                }
            }

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}