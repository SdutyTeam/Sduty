package com.d108.sduty.ui.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.TimeLineAdapter
import com.d108.sduty.databinding.FragmentTimeLineBinding
import com.d108.sduty.model.dto.Story
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.safeNavigate

// 타임라인 - 게시글(스크롤 뷰), 게시글 쓰기, 닉네임(게시글 상세페이지 이동) 더보기(신고, 스크랩, 팔로잉) ,좋아요, 댓글, 필터, 데일리 질문, 추천 팔로우
private const val TAG ="TimeLineFragment"
class TimeLineFragment : Fragment() {
    private lateinit var binding: FragmentTimeLineBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var timeLineAdapter: TimeLineAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeLineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        timeLineAdapter.list.add(Story())
        timeLineAdapter.list.add(Story())
        timeLineAdapter.list.add(Story())
        timeLineAdapter.list.add(Story())
        timeLineAdapter.list.add(Story())
    }

    private fun initView(){
        timeLineAdapter = TimeLineAdapter(requireActivity())
        timeLineAdapter.apply {
            onClickTimelineItem = object : TimeLineAdapter.TimeLineClickListener{
                override fun onFavoriteClicked(view: View, position: Int) {

                }
                override fun onScrapClicked(view: View, position: Int) {
                }
                override fun onReplyClicked(view: View, position: Int) {

                }
                override fun onMenuClicked(view: View, position: Int) {
                }
                override fun onProfileClicked(view: View, position: Int) {
                    findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToStoryDetailFragment())
                }
            }
        }
        binding.apply {
            lifecycleOwner = this@TimeLineFragment

            ivRegisterStory.setOnClickListener {
                findNavController().safeNavigate(
                    TimeLineFragmentDirections
                        .actionTimeLineFragmentToStoryRegisterFragment(null)
                )
            }
            recyclerTimeline.apply {
                adapter = timeLineAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

    }

    private fun initViewModel(){

    }
}
