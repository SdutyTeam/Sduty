package com.d108.sduty.ui.main.study

import android.content.Context
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyRegistBinding
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.main.study.viewmodel.StudyRegisteViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.showToast

// 스터디 등록 - 스터디 명, 공개 설정, 비밀번호 설정, 최대 인원, 카테고리, 스터디 설명, 일반/캠스터디 설정
private const val TAG ="StudyRegistFragment"
class StudyRegistFragment : Fragment() {
    private lateinit var binding: FragmentStudyRegistBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val studyRegisteViewModel: StudyRegisteViewModel by viewModels()
    private val args: StudyRegistFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
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
                binding.dailyTime.visibility = View.GONE
                binding.dailyWeek.visibility = View.GONE
                binding.dailyCheck.visibility = View.GONE
            }
        }

        val peopleData:Array<String> = resources.getStringArray(R.array.array_people)
        val peopleAdapter = ArrayAdapter(requireContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, peopleData)
        binding.spinnerPeople.adapter = peopleAdapter

        val categoryData:Array<String> = resources.getStringArray(R.array.array_category)
        val categoryAdapter = ArrayAdapter(requireContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, categoryData)
        binding.spinnerCategory.adapter = categoryAdapter

        binding.btnCreateStudy.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val hour = binding.timePicker.hour
                val minute = binding.timePicker.minute
            }
        }

        binding.apply {
            btnCreateStudy.setOnClickListener { studyCreate() }
            etStudyName.addTextChangedListener { textChangeListener }
            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }

            lifecycleOwner = this@StudyRegistFragment
            studyRegisteVM = studyRegisteViewModel

        }
    }

    fun studyCreate(){
        binding.apply {
            val name = etStudyName.text.toString().trim()
            val pass = etStduyPass.text.toString().trim()
            val introduce = etStudyIntroduce.text.toString().trim()
            if(name.isEmpty() || pass.isEmpty() || introduce.isEmpty()){
                context?.showToast("빈 칸을 모두 입력해 주세요.")
            }
            else{
                studyRegisteViewModel.study.observe(viewLifecycleOwner){
                    if(it != null){
                        // 성공적으로 스터디 생성

                    }
                }
                //studyRegisteViewModel.studyCreate(Study())
            }
        }
    }

    private val textChangeListener = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            studyRegisteViewModel.getStudyId(binding.etStudyName.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.displayBottomNav(true)
    }

}