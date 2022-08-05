package com.d108.sduty.ui.main.study

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudySettingBinding
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.study.viewmodel.StudySearchViewModel
import com.d108.sduty.ui.main.study.viewmodel.StudySettingViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showEditDialog
import com.d108.sduty.utils.showToast
import okhttp3.internal.notify

private const val TAG = "StudySettingFragment"
class StudySettingFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentStudySettingBinding
    private val studySettingViewModel: StudySettingViewModel by viewModels()
    private val args: StudySettingFragmentArgs by navArgs()
    private lateinit var studyDetail: Study

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudySettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studySettingViewModel.studyDetail(args.studySeq)

        studySettingViewModel.studyDetail.observe(viewLifecycleOwner){ study ->
            if(study != null){
                studyDetail = study
                binding.tvStudyName.text = studyDetail.name
                binding.tvStudyCategory.text = studyDetail.category
                binding.tvStudyPeople.text = studyDetail.joinNumber.toString()
                binding.tvStudyMaster.text = args.masterNickname

                if(studyDetail.password == null || studyDetail.password == ""){
                    binding.tvStudyPublic.text = "공개"
                } else{
                    binding.tvStudyPublic.text = "비공개"
                }


            }
        }

        studySettingViewModel.isStudyUpdate.observe(viewLifecycleOwner) {
            if(it){
                studySettingViewModel.studyDetail(args.studySeq)
                context?.showToast("수정한 내용이 적용되었습니다.")
            } else{
                context?.showToast("수정 권한이 없습니다.")
            }
        }

        studySettingViewModel.isDeleteStudy.observe(viewLifecycleOwner){
            if(it){
                context?.showToast("스터디 그룹이 삭제되었습니다.")
                findNavController().navigate(StudySettingFragmentDirections.actionStudySettingFragmentToMyStudyFragment())
            } else{
                context?.showToast("삭제 권한이 없습니다.")
            }
        }

        studySettingViewModel.isQuitStudy.observe(viewLifecycleOwner){
            if(it){
                context?.showToast("스터디 탈퇴가 완료되었습니다.")
                findNavController().navigate(StudySettingFragmentDirections.actionStudySettingFragmentToMyStudyFragment())
            }
        }

        binding.apply {

            ivStudyIntroduce.setOnClickListener {
                requireActivity().showAlertDialog("그룹 소개", studyDetail.introduce
                ) { dialog, which ->
                    when (which) { }
                }
            }

            ivStudyNoticeUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.notice)
                requireActivity().showEditDialog("공지사항 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studyDetail.notice = et.text.toString()
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }

            ivStudyNameUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.name)
                requireActivity().showEditDialog("그룹 명 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studyDetail.name = et.text.toString()
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }

            ivStudyCategoryUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.category)
                requireActivity().showEditDialog("카테고리 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studyDetail.category = et.text.toString()
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }

            ivStudyPeopleUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.limitNumber.toString())
                requireActivity().showEditDialog("모집인원 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studyDetail.limitNumber = et.text.toString().toInt()
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }

            ivStudyPasswordUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.password)
                requireActivity().showEditDialog("비밀번호 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            if(et.text.toString() == ""){
                                studyDetail.password = null
                            } else{
                                studyDetail.password = et.text.toString()
                            }
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }

            ivStudyIntroduceUpdate.setOnClickListener {
                val et = EditText(context)
                et.gravity = Gravity.CENTER
                et.setText(studyDetail.introduce)
                requireActivity().showEditDialog("그룹 소개 변경", et
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studyDetail.introduce = et.text.toString()
                            studySettingViewModel.studyUpdate(
                                mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                            )
                        }
                    }
                }
            }










            btnStudyQuit.setOnClickListener {
                requireActivity().showAlertDialog("그룹 탈퇴", "정말로 그룹을 탈퇴하시겠습니까?"
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studySettingViewModel.studyQuit(args.studySeq, mainViewModel.profile.value!!.userSeq)
                        }
                    }
                }
            }

            btnStudyDelete.setOnClickListener {
                requireActivity().showAlertDialog("그룹 삭제", "정말로 그룹을 삭제하시겠습니까?"
                ) { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            studySettingViewModel.studyDelete(mainViewModel.profile.value!!.userSeq, args.studySeq)
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