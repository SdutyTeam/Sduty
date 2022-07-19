package com.d108.sduty.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentPostDecoBinding

// 게시글 상세 - 게시글 사진, 더보기, 좋아요, 댓글 등록, 조회, 스크랩
private const val TAG ="PostDetailFragment"
class PostDetailFragment : Fragment() {
    private lateinit var binding: FragmentPostDecoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDecoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
