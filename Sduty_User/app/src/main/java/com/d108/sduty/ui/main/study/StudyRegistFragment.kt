package com.d108.sduty.ui.main.study

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStudyRegistBinding
import com.d108.sduty.model.dto.Alarm
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
    private var mon_state: Boolean = false
    private var tue_state: Boolean = false
    private var wed_state: Boolean = false
    private var thur_state: Boolean = false
    private var fri_state: Boolean = false
    private var sat_state: Boolean = false
    private var sun_state: Boolean = false

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

    @RequiresApi(Build.VERSION_CODES.M)
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



        binding.apply {
            btnCreateStudy.setOnClickListener { studyCreate() }
            etStudyName.addTextChangedListener(textChangeListener)
            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnMon.setOnClickListener {
                if(!mon_state){
                    mon_state = true
                    btnMon.setBackgroundColor(Color.GRAY)
                } else{
                    mon_state = false
                    btnMon.setBackgroundColor(Color.WHITE)
                }
            }
            btnTue.setOnClickListener {
                if(!tue_state){
                    tue_state = true
                    btnTue.setBackgroundColor(Color.GRAY)
                } else{
                    tue_state = false
                    btnTue.setBackgroundColor(Color.WHITE)
                }
            }
            btnWed.setOnClickListener {
                if(!wed_state){
                    wed_state = true
                    btnWed.setBackgroundColor(Color.GRAY)
                } else{
                    wed_state = false
                    btnWed.setBackgroundColor(Color.WHITE)
                }
            }
            btnThur.setOnClickListener {
                if(!thur_state){
                    thur_state = true
                    btnThur.setBackgroundColor(Color.GRAY)
                } else{
                    thur_state = false
                    btnThur.setBackgroundColor(Color.WHITE)
                }
            }
            btnFri.setOnClickListener {
                if(!fri_state){
                    fri_state = true
                    btnFri.setBackgroundColor(Color.GRAY)
                } else{
                    fri_state = false
                    btnFri.setBackgroundColor(Color.WHITE)
                }
            }
            btnSat.setOnClickListener {
                if(!sat_state){
                    sat_state = true
                    btnSat.setBackgroundColor(Color.GRAY)
                } else{
                    sat_state = false
                    btnSat.setBackgroundColor(Color.WHITE)
                }
            }
            btnSun.setOnClickListener {
                if(!sun_state){
                    sun_state = true
                    btnSun.setBackgroundColor(Color.GRAY)
                } else{
                    sun_state = false
                    btnSun.setBackgroundColor(Color.WHITE)
                }
            }


            checkBoxDaily.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnMon.setBackgroundColor(Color.GRAY)
                    btnTue.setBackgroundColor(Color.GRAY)
                    btnWed.setBackgroundColor(Color.GRAY)
                    btnThur.setBackgroundColor(Color.GRAY)
                    btnFri.setBackgroundColor(Color.GRAY)
                    btnSat.setBackgroundColor(Color.GRAY)
                    btnSun.setBackgroundColor(Color.GRAY)
                }else{
                    btnMon.setBackgroundColor(Color.WHITE)
                    btnTue.setBackgroundColor(Color.WHITE)
                    btnWed.setBackgroundColor(Color.WHITE)
                    btnThur.setBackgroundColor(Color.WHITE)
                    btnFri.setBackgroundColor(Color.WHITE)
                    btnSat.setBackgroundColor(Color.WHITE)
                    btnSun.setBackgroundColor(Color.WHITE)
                }
            }


            lifecycleOwner = this@StudyRegistFragment
            studyRegisteVM = studyRegisteViewModel

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun studyCreate(){
        binding.apply {
            val name = etStudyName.text.toString().trim()
            var pass = etStduyPass.text?.toString()?.trim()
            val introduce = etStudyIntroduce.text.toString().trim()
            val people = spinnerPeople.selectedItem.toString()
            val category = spinnerCategory.selectedItem.toString()
            val hour = binding.timePicker.hour.toString()
            val minute = binding.timePicker.minute




            if(name.isEmpty() || introduce.isEmpty()){
                context?.showToast("빈 칸을 모두 입력해 주세요.")
            }
            else{
                studyRegisteViewModel.createSuccess.observe(viewLifecycleOwner){
                    if(it){
                        // 성공적으로 스터디 생성 - 스터디 이동? 내 스터디 리스트?
                        findNavController().navigate(StudyRegistFragmentDirections.actionStudyRegistFragmentToMyStudyFragment())
                    }
                }
                if(pass == ""){
                    pass = null
                }
                if(!args.type) {
                    studyRegisteViewModel.studyCreate(
                        Study(
                            mainViewModel.profile.value!!.userSeq, name,
                            introduce, category, people.toInt(), pass, null
                        )
                    )
                } else{
                    studyRegisteViewModel.camStudyCreate(
                        Study(
                            mainViewModel.profile.value!!.userSeq,
                        name, introduce, category, people.toInt(), pass, "12345"
                        ), Alarm(0, "00:00:00", mon_state, tue_state, wed_state, thur_state, fri_state, sat_state, sun_state)
                    )
                }



            }
        }
    }

    private val textChangeListener = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            studyRegisteViewModel.getStudyId(binding.etStudyName.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.displayBottomNav(true)
    }

}