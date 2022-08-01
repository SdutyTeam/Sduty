package com.d108.sduty.ui.main.home

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty.databinding.FragmentStoryDecoBinding
import com.d108.sduty.databinding.FragmentStoryDetailBinding
import com.d108.sduty.databinding.FragmentStudyDetailBinding

// 게시글 상세 - 게시글 사진, 더보기, 좋아요, 댓글 등록, 조회, 스크랩
private const val TAG ="StoryDetailFragment"
class StoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentStoryDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        val displaymetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels
        val deviceHeight = deviceWidth / 3 * 4
        binding.apply {
            ivTimelineContent.layoutParams.width = deviceWidth
            ivTimelineContent.layoutParams.height = deviceHeight
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
