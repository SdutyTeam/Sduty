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
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.StudyListAdapter
import com.d108.sduty.databinding.FragmentStudyListBinding
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.sign.viewmodel.StudyListViewModel
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
    private var list = listOf<String>()


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
        //initAdapter()

//        studyListViewModel.list.observe(viewLifecycleOwner){
//            studyListAdapter.studyList = it
//            list = it
//        }
//        studyListViewModel.getList()


        binding.btnCreateStudyBy.setOnClickListener{
            // Dialog만들기
            val dialog = CustomDialog(mainActivity)
            dialog.showDialog()
            dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener{
                override fun onClicked(type: Boolean) {
                    findNavController().safeNavigate(StudyListFragmentDirections.actionStudyListFragmentToStudyRegistFragment(type))
                }
            })
        }

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
        studyListAdapter = StudyListAdapter(list)
        studyListAdapter.clickListener = object : StudyListAdapter.ClickListener{
            override fun onClick(view: View, position: Int) {
                // 전체 스터디 조회 - 스터디 클릭 시 상세정보 다이얼로그
                TODO("Not yet implemented")
            }
        }
        binding.studyList.apply {
            adapter = studyListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }


    }


}