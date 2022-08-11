package com.d108.sduty.ui.main.study

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.R
import com.d108.sduty.common.FLAG_STUDY
import com.d108.sduty.common.FLAG_STUDY_REGIST
import com.d108.sduty.databinding.FragmentStudySettingBinding
import com.d108.sduty.model.dto.Member
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.study.dialog.StudyDialog
import com.d108.sduty.ui.main.study.viewmodel.StudySettingViewModel
import com.d108.sduty.ui.sign.dialog.TagSelectOneFragment
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showEditDialog
import com.d108.sduty.utils.showToast
import com.sendbird.calls.shadow.com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

private const val TAG = "StudySettingFragment"
class StudySettingFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentStudySettingBinding
    private val studySettingViewModel: StudySettingViewModel by viewModels()
    private val args: StudySettingFragmentArgs by navArgs()
    private lateinit var studyDetail: Study
    private lateinit var myStudyMember: List<Map<String, Any>>
    private lateinit var myStudyInfo: Map<String, Any>
    private lateinit var nicknameList: ArrayList<String>
    private lateinit var nickname: String
    private var category: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudySettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val peopleData:Array<String> = resources.getStringArray(R.array.array_people)
        val peopleAdapter = ArrayAdapter(requireContext(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, peopleData)
        binding.spinnerPeople.adapter = peopleAdapter

        nickname = args.masterNickname
        studySettingViewModel.getMyStudyInfo(mainViewModel.profile.value!!.userSeq, args.studySeq)
        studySettingViewModel.studyDetail(args.studySeq)
        
        studySettingViewModel.myStudyInfo.observe(viewLifecycleOwner){
            val studyInfo = studySettingViewModel.myStudyInfo.value as Map<String, Any>

            myStudyMember = studyInfo["members"] as List<Map<String, Any>>
            myStudyInfo = studyInfo["study"] as Map<String, Any>

            nicknameList = ArrayList<String>()
            for(member in myStudyMember){
                nicknameList.add(member["nickname"] as String)
            }

            binding.tvStudyCategory.text = myStudyInfo["category"].toString()
            binding.etStudyUpdateName.setText(myStudyInfo["name"].toString())
            binding.etStduyPass.setText(myStudyInfo["password"].toString())

            if(myStudyInfo["password"] == null){
                binding.switchPass.isChecked = false
                binding.etStduyPass.text = null
                binding.switchPass.setOnCheckedChangeListener { buttonView, isChecked ->
                    binding.etStduyPass.isEnabled = isChecked
                    if(isChecked){
                        binding.etStduyPass.setBackgroundResource(0)
                    } else{
                        binding.etStduyPass.setBackgroundResource(R.drawable.study_gray)
                        binding.etStduyPass.text = null
                    }
                }
            } else{
                binding.switchPass.isChecked = true
                binding.switchPass.setOnCheckedChangeListener { buttonView, isChecked ->
                    binding.etStduyPass.isEnabled = isChecked
                    if(isChecked){
                        binding.etStduyPass.setBackgroundResource(0)
                    } else{
                        binding.etStduyPass.setBackgroundResource(R.drawable.study_gray)
                        binding.etStduyPass.text = null
                    }
                }
            }

            binding.spinnerPeople.setSelection((myStudyInfo["limitNumber"].toString().toDouble().roundToInt()) - 2)

            binding.etStudyIntroduce.setText(myStudyInfo["introduce"].toString())

            if(myStudyInfo["roomId"] == null){
                binding.studyDaily.visibility = View.GONE
                binding.tvDaily.visibility = View.GONE
            } else{
                val alarm = (myStudyInfo["alarm"] as Map<String, Any>)
                if(alarm["mon"] == true){
                    binding.btnMon.isChecked = true
                    binding.btnMon.setBackgroundResource(R.drawable.daily_click)
                    binding.btnMon.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["tue"] == true){
                    binding.btnTue.isChecked = true
                    binding.btnTue.setBackgroundResource(R.drawable.daily_click)
                    binding.btnTue.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["wed"] == true){
                    binding.btnWed.isChecked = true
                    binding.btnWed.setBackgroundResource(R.drawable.daily_click)
                    binding.btnWed.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["thu"] == true){
                    binding.btnThur.isChecked = true
                    binding.btnThur.setBackgroundResource(R.drawable.daily_click)
                    binding.btnThur.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["fri"] == true){
                    binding.btnFri.isChecked = true
                    binding.btnFri.setBackgroundResource(R.drawable.daily_click)
                    binding.btnFri.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["sat"] == true){
                    binding.btnSat.isChecked = true
                    binding.btnSat.setBackgroundResource(R.drawable.daily_click)
                    binding.btnSat.setTextColor(Color.parseColor("#9585EB"))
                }
                if(alarm["sun"] == true){
                    binding.btnSun.isChecked = true
                    binding.btnSun.setBackgroundResource(R.drawable.daily_click)
                    binding.btnSun.setTextColor(Color.parseColor("#9585EB"))
                }


                binding.timePicker.hour = alarm["time"].toString().substring(0 until 2).toInt()
                binding.timePicker.minute = alarm["time"].toString().substring(3 until 5).toInt()
            }




        }

        studySettingViewModel.studyDetail.observe(viewLifecycleOwner){

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

        binding.apply {
            etStudyUpdateName.addTextChangedListener(textChangeListener)
            btnUpdateStudy.setOnClickListener {
                // 변경 버튼 클릭
            }

            studyUpdateCategory.setOnClickListener {
                TagSelectOneFragment(requireContext(), FLAG_STUDY_REGIST).let{
                    it.show(parentFragmentManager, null)
                    it.onDismissDialogListener = object : TagSelectOneFragment.OnDismissDialogListener{
                        override fun onDismiss(tagName: String, flag: Int) {
                            tvStudyCategory.text = tagName
                            category = tagName
                        }
                    }
                }
            }

            btnMon.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnMon.setBackgroundResource(R.drawable.daily_click)
                    btnMon.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnMon.setBackgroundResource(R.drawable.border_study_solid)
                    btnMon.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnTue.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnTue.setBackgroundResource(R.drawable.daily_click)
                    btnTue.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnTue.setBackgroundResource(R.drawable.border_study_solid)
                    btnTue.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnWed.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnWed.setBackgroundResource(R.drawable.daily_click)
                    btnWed.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnWed.setBackgroundResource(R.drawable.border_study_solid)
                    btnWed.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnThur.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnThur.setBackgroundResource(R.drawable.daily_click)
                    btnThur.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnThur.setBackgroundResource(R.drawable.border_study_solid)
                    btnThur.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnFri.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnFri.setBackgroundResource(R.drawable.daily_click)
                    btnFri.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnFri.setBackgroundResource(R.drawable.border_study_solid)
                    btnFri.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnSat.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnSat.setBackgroundResource(R.drawable.daily_click)
                    btnSat.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnSat.setBackgroundResource(R.drawable.border_study_solid)
                    btnSat.setTextColor(Color.parseColor("#979797"))
                }
            }

            btnSun.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    btnSun.setBackgroundResource(R.drawable.daily_click)
                    btnSun.setTextColor(Color.parseColor("#9585EB"))
                } else{
                    btnSun.setBackgroundResource(R.drawable.border_study_solid)
                    btnSun.setTextColor(Color.parseColor("#979797"))
                }
            }

            ivStudyMasterUpdate.setOnClickListener {
                val items = Array<String>(nicknameList.size) { "" }
                for(i in 0 until nicknameList.size){
                    items[i] = nicknameList[i]
                }
                var selectedItem: String? = null
                AlertDialog.Builder(context)
                    .setTitle("그룹 장 변경")
                    .setSingleChoiceItems(items, -1) { dialog, which ->
                        selectedItem = items[which]        }
                    .setPositiveButton("변경") { dialog, which ->
                        for(member in myStudyMember){
                            if(member["nickname"] as String == selectedItem.toString()){
                                studyDetail.masterSeq = member["userSeq"].toString().toDouble().roundToInt()
                                nickname = selectedItem.toString()
                                studySettingViewModel.studyUpdate(
                                    mainViewModel.profile.value!!.userSeq, args.studySeq, studyDetail
                                )
                            }
                        }
                    }
                    .setNegativeButton("취소", null)
                    .show()
            }

            ivStudyPeopleBan.setOnClickListener {
                // 그룹원 강퇴
            }

            btnStudyDelete.setOnClickListener {
                val dialog = StudyDialog(mainActivity, "삭제하시겠습니까?", "가입하신 스터디를 삭제합니다.", "삭제", "취소")
                dialog.showDialog()
                dialog.setOnClickListener(object : StudyDialog.OnDialogClickListener{
                    override fun onClicked() {
                        studySettingViewModel.studyDelete(mainViewModel.profile.value!!.userSeq, args.studySeq)
                    }
                })
            }

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            studySettingViewModel.getStudyId(binding.etStudyUpdateName.text.toString())
        }

    }


}