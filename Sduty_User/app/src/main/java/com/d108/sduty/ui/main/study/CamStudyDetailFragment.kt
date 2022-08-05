package com.d108.sduty.ui.main.study

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentCamstudyDetailBinding
import com.d108.sduty.ui.main.study.viewmodel.CamStudyDetailViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate
import kotlin.math.roundToInt


// 캠 스터디 상세
private const val TAG = "CamStudyDetailFragment"
class CamStudyDetailFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCamstudyDetailBinding
    private val camStudyDetailViewModel: CamStudyDetailViewModel by viewModels()
    private val args: CamStudyDetailFragmentArgs by navArgs()
    lateinit var nickname: String


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCamstudyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        camStudyDetailViewModel.camStudyInfo.observe(viewLifecycleOwner){map ->
            if(map != null){
                val studyInfo = camStudyDetailViewModel.camStudyInfo.value as Map<String, Any>
                var asd = ((studyInfo["study"] as Map<String, Any>)["joinNumber"].toString()).toDouble()
                binding.commonTopTitle.text = (studyInfo["study"] as Map<String, Any>)["name"].toString()
                binding.commonTopJoin.text = asd.roundToInt().toString()
                binding.tvCamstudyNotice.text = (studyInfo["study"] as Map<String, Any>)["notice"].toString()

                camStudyDetailViewModel.masterNickname((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt())
                camStudyDetailViewModel.studyMasterNickName.observe(viewLifecycleOwner) {
                    nickname = it.nickname
                }
            }
        }

        camStudyDetailViewModel.getCamStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)

        binding.apply {

            btnJoinCamstudy.setOnClickListener {
                findNavController().safeNavigate(CamStudyDetailFragmentDirections.actionCamStudyDetailFragmentToPreviewFragment())
            }

            btnStudySetting.setOnClickListener {
                findNavController().safeNavigate(CamStudyDetailFragmentDirections.actionCamStudyDetailFragmentToStudySettingFragment(args.studySeq, nickname))
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