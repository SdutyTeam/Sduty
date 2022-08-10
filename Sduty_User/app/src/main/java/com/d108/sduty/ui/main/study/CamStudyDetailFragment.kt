package com.d108.sduty.ui.main.study

import android.content.Context
import android.graphics.Color
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
    lateinit var studyRoomId: String
    lateinit var studyName: String


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
                Log.d(TAG, "onViewCreated: ${studyInfo}")
                var joinNum = ((studyInfo["study"] as Map<String, Any>)["joinNumber"].toString()).toDouble()
                var limitNum = ((studyInfo["study"] as Map<String, Any>)["limitNumber"].toString()).toDouble()


                if((studyInfo["study"] as Map<String, Any>)["password"] == null){
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_unlock)
                } else{
                    binding.imgStudyLock.setImageResource(R.drawable.img_study_detail_lock)
                }

                binding.studyDetailCategory.text = "#캠스터디" + "#" + (studyInfo["study"] as Map<String, Any>)["category"].toString()

                binding.studyDetailName.text = (studyInfo["study"] as Map<String, Any>)["name"].toString()
                binding.studyDetailJoinnum.text = joinNum.roundToInt().toString()
                binding.studyDetailLimitnum.text = limitNum.roundToInt().toString()
                binding.studyDetailIntroduce.text = (studyInfo["study"] as Map<String, Any>)["introduce"].toString()
                binding.studyDetailNotice.text = (studyInfo["study"] as Map<String, Any>)["notice"].toString()

                studyRoomId = (studyInfo["study"] as Map<String, Any>)["roomId"].toString()
                studyName = (studyInfo["study"] as Map<String, Any>)["name"].toString()

                val alarm = ((studyInfo["study"] as Map<String, Any>)["alarm"] as Map<String, Any>)
                if(alarm["mon"] == true){
                    binding.tvMon.setBackgroundResource(R.drawable.daily_click)
                    binding.tvMon.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["tue"] == true){
                    binding.tvTue.setBackgroundResource(R.drawable.daily_click)
                    binding.tvTue.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["wed"] == true){
                    binding.tvWed.setBackgroundResource(R.drawable.daily_click)
                    binding.tvWed.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["thu"] == true){
                    binding.tvThu.setBackgroundResource(R.drawable.daily_click)
                    binding.tvThu.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["fri"] == true){
                    binding.tvFri.setBackgroundResource(R.drawable.daily_click)
                    binding.tvFri.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["sat"] == true){
                    binding.tvSat.setBackgroundResource(R.drawable.daily_click)
                    binding.tvSat.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["sun"] == true){
                    binding.tvSun.setBackgroundResource(R.drawable.daily_click)
                    binding.tvSun.setTextColor(Color.parseColor("#9585EB"))
                }
                binding.tvTime.text = "매주" + alarm["time"].toString()


                camStudyDetailViewModel.masterNickname((studyInfo["study"] as Map<String, Any>)["masterSeq"].toString().toDouble().roundToInt())
                camStudyDetailViewModel.studyMasterNickName.observe(viewLifecycleOwner) {
                    nickname = it.nickname
                    binding.studyDetailMaster.text = nickname
                }
            }
        }

        camStudyDetailViewModel.getCamStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)

        binding.apply {

            btnJoinCamstudy.setOnClickListener {
                findNavController().safeNavigate(CamStudyDetailFragmentDirections.actionCamStudyDetailFragmentToPreviewFragment(studyRoomId, studyName))
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