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
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.StudyListAdapter
import com.d108.sduty.databinding.FragmentStudyListBinding
import com.d108.sduty.model.dto.Study
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.study.dialog.StudyCreateDialog
import com.d108.sduty.ui.main.study.dialog.StudyDetailDialog
import com.d108.sduty.ui.main.study.viewmodel.StudyListViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate

// 스터디 전체 리스트
private const val TAG = "StudyListFragment"
class StudyListFragment : Fragment(){
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentStudyListBinding

    private val studyListViewModel: StudyListViewModel by viewModels()
    private lateinit var studyListAdapter: StudyListAdapter
    private lateinit var studyList: List<Study>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studyListViewModel.studyList.observe(viewLifecycleOwner){
            if(it != null){
                studyList = studyListViewModel.studyList.value as List<Study>
                initAdapter()
            }
        }
        studyListViewModel.getStudyList()


        binding.btnSearch.setOnClickListener {
            findNavController().safeNavigate(StudyListFragmentDirections.actionStudyListFragmentToStudySearchFragment())
        }

        binding.commonTopBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.displayBottomNav(true)
    }

    private fun initAdapter(){
        studyListAdapter = StudyListAdapter(studyList)
        studyListAdapter.onStudyItemClick = object : StudyListAdapter.OnStudyItemClick{
            override fun onClick(view: View, position: Int) {
                Log.d(TAG, "onClick: ${studyList}")
                // 전체 스터디 조회 - 스터디 클릭 시 상세정보 다이얼로그
                val dialog = StudyDetailDialog(mainActivity)
                dialog.showDialog()
                dialog.setOnClickListener(object : StudyDetailDialog.OnDialogClickListener{
                    override fun onClicked() {
                    }

                })
            }
        }
        binding.studyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = studyListAdapter
        }
    }


}