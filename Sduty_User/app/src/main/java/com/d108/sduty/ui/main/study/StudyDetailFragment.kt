package com.d108.sduty.ui.main.study

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.d108.sduty.ui.main.study.viewmodel.StudyDetailViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import kotlin.math.roundToInt

// 스터디 상세 -
private const val TAG = "StudyDetailFragment"
class StudyDetailFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentStudyDetailBinding
    private val studyDetailViewModel: StudyDetailViewModel by viewModels()
    private val args: StudyDetailFragmentArgs by navArgs()
    lateinit var nickname: String

    private lateinit var studyMemberAdapter: StudyMemeberAdapter
    private lateinit var studyMember: List<Member>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studyDetailViewModel.myStudyInfo.observe(viewLifecycleOwner){ map ->
            if(map != null){
                val studyInfo = studyDetailViewModel.myStudyInfo.value as Map<String, Any>
                Log.d(TAG, "onViewCreatedaa: ${studyInfo}")

                studyMember = studyInfo["members"] as List<Member>
                //initAdapter()

                var joinNum = ((studyInfo["study"] as Map<String, Any>)["joinNumber"].toString()).toDouble()
                var limitNum = ((studyInfo["study"] as Map<String, Any>)["limitNumber"].toString()).toDouble()

                if((studyInfo["study"] as Map<String, Any>)["password"] == null){
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_unlock)
                } else{
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_lock)
                }

                binding.studyDetailCategory.text = "#일반스터디" + "#" + (studyInfo["study"] as Map<String, Any>)["category"].toString()

                binding.studyDetailName.text = (studyInfo["study"] as Map<String, Any>)["name"].toString()
                binding.studyDetailJoinnum.text = joinNum.roundToInt().toString()
                binding.studyDetailLimitnum.text = limitNum.roundToInt().toString()
                binding.studyDetailIntroduce.text = (studyInfo["study"] as Map<String, Any>)["introduce"].toString()
                binding.studyDetailNotice.text = (studyInfo["study"] as Map<String, Any>)["notice"].toString()


                

                studyDetailViewModel.masterNickname((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt())
                studyDetailViewModel.studyMasterNickName.observe(viewLifecycleOwner) {
                    nickname = it.nickname
                    binding.studyDetailMaster.text = nickname
                }
            }
        }

        studyDetailViewModel.getMyStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)

        binding.apply {
            btnStudySetting.setOnClickListener {
                findNavController().safeNavigate(StudyDetailFragmentDirections.actionStudyDetailFragmentToStudySettingFragment(args.studySeq, nickname))
            }

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initAdapter(){
        studyMemberAdapter = StudyMemeberAdapter(studyMember)
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