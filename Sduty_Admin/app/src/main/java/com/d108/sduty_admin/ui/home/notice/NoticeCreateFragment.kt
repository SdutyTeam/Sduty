package com.d108.sduty_admin.ui.home.notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty_admin.R
import com.d108.sduty_admin.databinding.FragmentNoticeCreateBinding

class NoticeCreateFragment : Fragment() {
    private lateinit var binding: FragmentNoticeCreateBinding
    private val noticeCreateViewModel: NoticeCreateViewModel by viewModels()


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

            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}