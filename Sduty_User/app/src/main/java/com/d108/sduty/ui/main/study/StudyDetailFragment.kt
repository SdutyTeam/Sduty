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
import com.d108.sduty.ui.main.study.viewmodel.StudyDetailViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate

// 스터디 상세 -
private const val TAG = "StudyDetailFragment"
class StudyDetailFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentStudyDetailBinding
    private val studyDetailViewModel: StudyDetailViewModel by viewModels()
    private val args: StudyDetailFragmentArgs by navArgs()


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

        studyDetailViewModel.getMyStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)

        binding.apply {




            btnStudySetting.setOnClickListener {
                findNavController().safeNavigate(StudyDetailFragmentDirections.actionStudyDetailFragmentToStudySettingFragment())
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