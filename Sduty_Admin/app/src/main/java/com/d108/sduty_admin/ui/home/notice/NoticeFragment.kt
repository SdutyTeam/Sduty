package com.d108.sduty_admin.ui.home.notice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty_admin.R
import com.d108.sduty_admin.adapter.NoticeAdapter
import com.d108.sduty_admin.databinding.FragmentNoticeBinding
import com.d108.sduty_admin.model.dto.Notice

class NoticeFragment : Fragment() {
    private lateinit var binding: FragmentNoticeBinding
    private val noticeListViewModel: NoticeViewModel by viewModels()
    private lateinit var noticeList: List<Notice>
    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeListViewModel.getNoticeList()

        noticeListViewModel.noticeList.observe(viewLifecycleOwner) {
            if(it != null) {
                noticeList = noticeListViewModel.noticeList.value as List<Notice>
                initAdapter()
            }
        }

        noticeListViewModel.noticeDelete.observe(viewLifecycleOwner) {
            if(it){
                Toast.makeText(context, "선택한 공지사항이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.apply {

            btnNoticeCreate.setOnClickListener {
                findNavController().navigate(NoticeFragmentDirections.actionNoticeFragmentToNoticeCreateFragment("create"))
            }

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }

    private fun initAdapter() {
        noticeAdapter = NoticeAdapter(noticeList)

        noticeAdapter.apply {
            onClickNoticeItem = object : NoticeAdapter.NoticeListClickListener{
                override fun onEditClicked(view: View, position: Int) {
                    findNavController().navigate(NoticeFragmentDirections.actionNoticeFragmentToNoticeCreateFragment("edit", noticeList[position].content, noticeList[position].seq))
                }

                override fun onDeleteClicked(view: View, position: Int) {
                    noticeListViewModel.deleteNotice(noticeAdapter.list[position].seq)
                }
            }
        }


        binding.noticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
        }
    }



}