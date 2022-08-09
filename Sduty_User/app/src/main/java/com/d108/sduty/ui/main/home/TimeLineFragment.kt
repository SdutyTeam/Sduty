package com.d108.sduty.ui.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.TimeLineAdapter
import com.d108.sduty.common.ALL_TAG
import com.d108.sduty.common.FLAG_TIMELINE
import com.d108.sduty.common.INTEREST_TAG
import com.d108.sduty.common.JOB_TAG
import com.d108.sduty.databinding.FragmentTimeLineBinding
import com.d108.sduty.model.dto.*
import com.d108.sduty.ui.sign.dialog.TagSelectOneFragment
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showToast

// 타임라인 - 게시글(스크롤 뷰), 게시글 쓰기, 닉네임(게시글 상세페이지 이동) 더보기(신고, 스크랩, 팔로잉) ,좋아요, 댓글, 필터, 데일리 질문, 추천 팔로우
private const val TAG ="TimeLineFragment"
class TimeLineFragment : Fragment(), PopupMenu.OnMenuItemClickListener   {
    private lateinit var binding: FragmentTimeLineBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val storyViewModel: StoryViewModel by activityViewModels()
    private lateinit var timeLineAdapter: TimeLineAdapter
    private var selectedPosition = 0
    private var timeLineList = mutableListOf<Timeline>()

    override fun onResume() {
        super.onResume()
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
        initViewModel()
        initView()
    }

    private fun initView(){
        timeLineAdapter = TimeLineAdapter(requireActivity())
        timeLineAdapter.apply {
            onClickTimelineItem = object : TimeLineAdapter.TimeLineClickListener{
                override fun onFavoriteClicked(view: View, position: Int) {
                    storyViewModel.likeStoryInTimeLine(Likes(mainViewModel.user.value!!.seq, timeLineAdapter.list[position].story.seq))
                    if(timeLineList[position].likes)timeLineList[position].numLikes--
                    else timeLineList[position].numLikes++
                    timeLineList[position].likes = !timeLineList[position].likes
                    timeLineAdapter.list = timeLineList
                }
                override fun onScrapClicked(view: View, position: Int) {
                    storyViewModel.scrapStory(Scrap(mainViewModel.user.value!!.seq, timeLineAdapter.list[position].story.seq))
                    timeLineList[position].scrap = !timeLineList[position].scrap
                    timeLineAdapter.list = timeLineList
                }
                override fun onReplyClicked(view: View, position: Int) {
                    findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToStoryDetailFragment(timeLineList[position].story.seq))
//                    if(timeLineAdapter.list[position].profile.userSeq != mainViewModel.user.value!!.seq)
//                        findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToUserProfileFragment(timeLineAdapter.list[position].profile.userSeq))
//                    else{
//                        findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToMyPageFragment())
//                    }
                }
                override fun onMenuClicked(view: View, position: Int) {
                    selectedPosition = position
                    PopupMenu(requireContext(), view).apply {
                        setOnMenuItemClickListener(this@TimeLineFragment)
                        inflate(R.menu.menu_story_visiters)
                        if (mainViewModel.checkFollowUser(timeLineAdapter.list[position].story.writerSeq)) {
                            menu[0].title = "언팔로우"
                        }else{
                            menu[0].title = "팔로우"
                        }
                        show()
                    }
                }
                override fun onProfileClicked(view: View, position: Int) {
                    if(timeLineAdapter.list[position].profile.userSeq != mainViewModel.user.value!!.seq)
                        findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToUserProfileFragment(timeLineAdapter.list[position].profile.userSeq))
                    else{
                        findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToMyPageFragment())
                    }
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
            ivFilter.setOnClickListener {
                TagSelectOneFragment(requireContext(), FLAG_TIMELINE).let{
                    it.show(parentFragmentManager, null)
                    it.onDismissDialogListener = object : TagSelectOneFragment.OnDismissDialogListener{
                        override fun onDismiss(tagName: String, flag: Int) {
                            when(flag){
                                JOB_TAG -> storyViewModel.getTimelineJobAllList(mainViewModel.user.value!!.seq, tagName)
                                INTEREST_TAG -> storyViewModel.getTimelineInterestList(mainViewModel.user.value!!.seq, tagName)
                                ALL_TAG -> storyViewModel.getStoryListValue(mainViewModel.user.value!!.seq)
                            }
                        }
                    }
                }
            }
        }

    }

    private fun initViewModel(){
        storyViewModel.timelineList.observe(viewLifecycleOwner){
            timeLineList = it
            timeLineAdapter.list = it
        }

        storyViewModel.filteredTimelineList.observe(viewLifecycleOwner){
            timeLineList = it
            timeLineAdapter.list = it
        }

        storyViewModel.getStoryListValue(mainViewModel.user.value!!.seq)
        mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.follow -> {
                storyViewModel.doFollow(Follow(mainViewModel.user.value!!.seq, timeLineAdapter.list[selectedPosition].story.writerSeq))
                mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
                if (mainViewModel.checkFollowUser(timeLineAdapter.list[selectedPosition].story.writerSeq)) {
                    item.title = "언팔로우"
                }else{
                    item.title = "팔로우"
                }
            }
            R.id.report ->{

            }
        }
        return true
    }
}
