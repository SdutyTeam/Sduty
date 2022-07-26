package com.d108.sduty.ui.main.study

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyRegistBinding
import com.d108.sduty.ui.viewmodel.MainViewModel

// 스터디 등록 - 스터디 명, 공개 설정, 비밀번호 설정, 최대 인원, 카테고리, 스터디 설명, 일반/캠스터디 설정
private const val TAG ="StudyRegistFragment"
class StudyRegistFragment : Fragment() {
    private lateinit var binding: FragmentStudyRegistBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val args: StudyRegistFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
        Log.d(TAG, "onAttach: ${mainViewModel.visibilityBottomNav.value}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(args.type){
            false -> {
                binding.daily.visibility = View.INVISIBLE
            }
        }

        val peopleData:Array<String> = resources.getStringArray(R.array.array_people)
        val peopleAdapter = ArrayAdapter(requireContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, peopleData)
        binding.spinnerPeople.adapter = peopleAdapter

        val categoryData:Array<String> = resources.getStringArray(R.array.array_category)
        val categoryAdapter = ArrayAdapter(requireContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, categoryData)
        binding.spinnerCategory.adapter = categoryAdapter

    }

}