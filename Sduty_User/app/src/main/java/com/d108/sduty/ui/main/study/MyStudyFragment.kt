package com.d108.sduty.ui.main.study

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.adapter.MyStudyAdapter
import com.d108.sduty.adapter.StudyListAdapter
import com.d108.sduty.databinding.FragmentMyStudyBinding
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.study.dialog.StudyCreateDialog
import com.d108.sduty.ui.main.study.dialog.StudyDetailDialog
import com.d108.sduty.ui.main.study.viewmodel.MyStudyViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate

// 스터디 - 가입된 스터디 목록(스터디 이름, 카테고리/직업, 참여/제한 인원, 방장 별명), 스터디 상세보기 이동, 스터디 등록, 스터디 검색
private const val TAG ="MyStudyFragment"
class MyStudyFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMyStudyBinding
    private val myStudyViewModel: MyStudyViewModel by viewModels()

    private lateinit var mystudyListAdapter: StudyListAdapter
    private lateinit var mystudyList: List<Study>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyStudyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myStudyViewModel.myStudyList.observe(viewLifecycleOwner){
            if(it != null){
                mystudyList = myStudyViewModel.myStudyList.value as List<Study>
                initAdapter()
            }
        }

        //myStudyViewModel.getMyStudyList()



        binding.btnCreateStudy.setOnClickListener{
            // Dialog만들기
            val dialog = StudyCreateDialog(mainActivity)
            dialog.showDialog()
            dialog.setOnClickListener(object : StudyCreateDialog.OnDialogClickListener{
                override fun onClicked(type: Boolean) {
                    findNavController().safeNavigate(MyStudyFragmentDirections.actionMyStudyFragmentToStudyRegistFragment(type))
                }
            })
        }

        binding.btnListStudy.setOnClickListener {
            findNavController().safeNavigate(MyStudyFragmentDirections.actionMyStudyFragmentToStudyListFragment())
        }

    }


    private fun initAdapter(){
        mystudyListAdapter = StudyListAdapter(mystudyList)
        mystudyListAdapter.onStudyItemClick = object : StudyListAdapter.OnStudyItemClick{
            override fun onClick(view: View, position: Int) {
                // 선택 스터디 입장


            }
        }
        binding.myStudyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mystudyListAdapter
        }
    }
}