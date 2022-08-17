package com.d108.sduty_admin.ui.home.notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty_admin.R
import com.d108.sduty_admin.databinding.FragmentNoticeCreateBinding
import com.d108.sduty_admin.model.dto.Notice
import com.d108.sduty_admin.ui.MainViewModel

class NoticeCreateFragment : Fragment() {
    private lateinit var binding: FragmentNoticeCreateBinding
    private val noticeCreateViewModel: NoticeCreateViewModel by viewModels()
    private val viewModel: MainViewModel by activityViewModels()
    private val args: NoticeCreateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if(args.type == "create"){
                btnCreateNotice.setOnClickListener {
                    val et = etNotice.text.toString()
                    if(et.isEmpty()){
                        Toast.makeText(context, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
                    } else {
                        val notice = Notice(0, et, viewModel.admin.value!!.seq)
                        noticeCreateViewModel.createNotice(notice)
                    }
                }
            } else{
                btnCreateNotice.text = "변경"
                etNotice.setText(args.content)
                btnCreateNotice.setOnClickListener {
                    if(etNotice.text.isEmpty()){
                        Toast.makeText(context, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
                    } else {
                        val notice = Notice(0, etNotice.text.toString(), viewModel.admin.value!!.seq)
                        noticeCreateViewModel.createNotice(notice)
                    }
                }
            }


            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        noticeCreateViewModel.createSuccess.observe(viewLifecycleOwner) {
            if(it) {
                Toast.makeText(context, "공지사항이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        noticeCreateViewModel.updateSuccess.observe(viewLifecycleOwner) {
            if(it) {
                Toast.makeText(context, "공지사항이 수정되었습니다.", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
}