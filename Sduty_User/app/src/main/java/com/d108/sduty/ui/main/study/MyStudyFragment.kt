package com.d108.sduty.ui.main.study

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.MyStudyAdapter
import com.d108.sduty.databinding.FragmentMyStudyBinding
import com.d108.sduty.ui.sign.viewmodel.MyStudyViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel

// 스터디 - 가입된 스터디 목록(스터디 이름, 카테고리/직업, 참여/제한 인원, 방장 별명), 스터디 상세보기 이동, 스터디 등록, 스터디 검색
private const val TAG ="MyStudyFragment"
class MyStudyFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMyStudyBinding
    private val myStudyViewModel: MyStudyViewModel by viewModels()

    private lateinit var myStudyAdapter: MyStudyAdapter
    private var list = listOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
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
        initAdapter()

        myStudyViewModel.list.observe(viewLifecycleOwner){
            myStudyAdapter.myStudyList = it
            list = it
        }

        myStudyViewModel.getList()

        binding.btnCreateStudy.setOnClickListener{
            // Dialog만들기
            //val mDialogView: View = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
            val mDialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .create()





            mBuilder.show()
            mBuilder.window?.setGravity(Gravity.TOP)
            mBuilder.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            mBuilder.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)


        }

    }


    private fun initAdapter(){
        myStudyAdapter = MyStudyAdapter(list)
        myStudyAdapter.clickListener = object : MyStudyAdapter.ClickListener{
            override fun onClick(view: View, position: Int) {
                // 스터디 입장 (내 스터디 클릭 이벤트)
                TODO("Not yet implemented")
            }

        }

        binding.mystudyList.apply {
            adapter = myStudyAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}