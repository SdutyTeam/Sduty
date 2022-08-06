package com.d108.sduty.ui.main.study

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyDetailBinding
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
                var asd = ((studyInfo["study"] as Map<String, Any>)["joinNumber"].toString()).toDouble()
                binding.commonTopTitle.text = (studyInfo["study"] as Map<String, Any>)["name"].toString()
                binding.commonTopJoin.text = asd.roundToInt().toString()
                binding.tvMyStudyNotice.text = (studyInfo["study"] as Map<String, Any>)["notice"].toString()

                studyDetailViewModel.masterNickname((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt())
                studyDetailViewModel.studyMasterNickName.observe(viewLifecycleOwner) {
                    nickname = it.nickname
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

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.displayBottomNav(true)
    }

}