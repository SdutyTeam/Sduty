package com.d108.sduty.ui.main.study

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.CamstudyMemberAdapter
import com.d108.sduty.adapter.StudyMemeberAdapter
import com.d108.sduty.databinding.FragmentStudyDetailBinding
import com.d108.sduty.model.dto.Alarm
import com.d108.sduty.model.dto.Member
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.study.dialog.StudyCheckDialog
import com.d108.sduty.ui.main.study.dialog.StudyDialog
import com.d108.sduty.ui.main.study.dialog.StudyPasswordDialog
import com.d108.sduty.ui.main.study.viewmodel.StudyDetailViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showToast
import com.fasterxml.jackson.annotation.JsonFormat
import com.google.gson.GsonBuilder
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.math.roundToInt

// 스터디 상세 -
private const val TAG = "StudyDetailFragment"
class StudyDetailFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentStudyDetailBinding
    private val studyDetailViewModel: StudyDetailViewModel by viewModels()
    private val args: StudyDetailFragmentArgs by navArgs()
    lateinit var masterNickname: String
    lateinit var masterJob: String

    private lateinit var studyMemberAdapter: StudyMemeberAdapter
    private lateinit var studyMember: String


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    inline fun <reified T> parseArray(json: String, typeToken: Type): T{
        val gson = GsonBuilder().setDateFormat("HH:mm:ss").create()
        return gson.fromJson<T>(json, typeToken)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studyDetailViewModel.myStudyInfo.observe(viewLifecycleOwner){ map ->
            if(map != null){
                val studyInfo = studyDetailViewModel.myStudyInfo.value as Map<String, Any>

//                studyMember = studyInfo["members"].toString()
//                Log.d(TAG, "onViewCreated: ${studyMember}")
//                val type = object : TypeToken<List<Member>>() {}.type
//                val result: List<Member> = parseArray<List<Member>>(json = studyMember, typeToken = type)
//                Log.d(TAG, "onViewCreated: ${result}")



                //initAdapter()

                var joinNum = ((studyInfo["study"] as Map<String, Any>)["joinNumber"].toString()).toDouble()
                var limitNum = ((studyInfo["study"] as Map<String, Any>)["limitNumber"].toString()).toDouble()

                if((studyInfo["study"] as Map<String, Any>)["password"] == null){
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_unlock)
                } else{
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_lock)
                }

                if((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt() == mainViewModel.profile.value!!.userSeq){
                    binding.btnStudySetting.visibility = View.VISIBLE
                } else{
                    binding.btnStudyExit.visibility = View.VISIBLE
                }

                binding.studyDetailCategory.text = "#일반스터디" + "#" + (studyInfo["study"] as Map<String, Any>)["category"].toString()

                binding.studyDetailName.text = (studyInfo["study"] as Map<String, Any>)["name"].toString()
                binding.studyDetailJoinnum.text = joinNum.roundToInt().toString()
                binding.studyDetailLimitnum.text = limitNum.roundToInt().toString()
                binding.studyDetailIntroduce.text = (studyInfo["study"] as Map<String, Any>)["introduce"].toString()
                binding.studyDetailNotice.text = (studyInfo["study"] as Map<String, Any>)["notice"].toString()

                binding.studyDetailIntroduce.setOnClickListener {
                    val dialog = StudyCheckDialog(mainActivity, "그룹 소개", (studyInfo["study"] as Map<String, Any>)["introduce"].toString() )
                    dialog.showDialog()
                }
                binding.studyDetailNotice.setOnClickListener {
                    val dialog = StudyCheckDialog(mainActivity, "공지사항", (studyInfo["study"] as Map<String, Any>)["notice"].toString() )
                    dialog.showDialog()
                }


                

                studyDetailViewModel.masterNickname((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt())
                studyDetailViewModel.studyMasterNickName.observe(viewLifecycleOwner) {
                    masterNickname = it.nickname
                    masterJob = it.job
                    binding.studyDetailMaster.text = masterNickname
                    binding.studyDetailCategory.text = "#일반스터디" + "#" + masterJob + "#" + (studyInfo["study"] as Map<String, Any>)["category"].toString()
                }
            }
        }

        studyDetailViewModel.isQuitStudy.observe(viewLifecycleOwner){
            if(it){
                context?.showToast("스터디 탈퇴가 완료되었습니다.")
                findNavController().popBackStack()
            }
        }

        studyDetailViewModel.getMyStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)

        binding.apply {
            btnStudySetting.setOnClickListener {
                findNavController().safeNavigate(StudyDetailFragmentDirections.actionStudyDetailFragmentToStudySettingFragment(args.studySeq, masterNickname))
            }

            btnStudyExit.setOnClickListener {
                val dialog = StudyDialog(mainActivity, "탈퇴하시겠습니까?", "가입하신 스터디에서 탈퇴합니다.", "탈퇴", "취소")
                dialog.showDialog()
                dialog.setOnClickListener(object : StudyDialog.OnDialogClickListener{
                    override fun onClicked() {
                        studyDetailViewModel.studyQuit(args.studySeq, mainViewModel.profile.value!!.userSeq)
                    }
                })
            }

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initAdapter(){
        //studyMemberAdapter = StudyMemeberAdapter(studyMember)
        binding.studyMember.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = studyMemberAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.displayBottomNav(true)
    }

}