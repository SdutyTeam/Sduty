package com.d108.sduty.ui.main.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.TimeLineAdapter
import com.d108.sduty.adapter.paging.TimeLinePagingAdapter
import com.d108.sduty.common.ALL_TAG
import com.d108.sduty.common.FLAG_TIMELINE
import com.d108.sduty.common.INTEREST_TAG
import com.d108.sduty.common.JOB_TAG
import com.d108.sduty.databinding.FragmentTimeLineBinding
import com.d108.sduty.model.dto.*
import com.d108.sduty.ui.main.home.dialog.PushInfoDialog
import com.d108.sduty.ui.main.mypage.setting.viewmodel.SettingViewModel
import com.d108.sduty.ui.sign.dialog.TagSelectOneFragment
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.SettingsPreference
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.sharedpreference.FCMPreference
import com.d108.sduty.utils.showToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

// 타임라인 - 게시글(스크롤 뷰), 게시글 쓰기, 닉네임(게시글 상세페이지 이동) 더보기(신고, 스크랩, 팔로잉) ,좋아요, 댓글, 필터, 데일리 질문, 추천 팔로우
private const val TAG ="TimeLineFragment"
class TimeLineFragment : Fragment(), PopupMenu.OnMenuItemClickListener   {
    private lateinit var binding: FragmentTimeLineBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val storyViewModel: StoryViewModel by activityViewModels()
    private val settingViewModel: SettingViewModel by viewModels()
    private lateinit var timeLineAdapter: TimeLineAdapter
    private var selectedPosition = 0
    private var timeLineList = mutableListOf<Timeline>()

    private lateinit var pageAdapter: TimeLinePagingAdapter
    private lateinit var menuSelectedTimeline: Timeline
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(){
        if(SettingsPreference().getFirstLoginCheck()){
            PushInfoDialog().let{
                it.show(parentFragmentManager, null)
                it.onClickConfirm = object :PushInfoDialog.OnClickConfirm{
                    override fun onClick(state: Int) {
                        SettingsPreference().setPushState(state)
                        getFirebaseToken()
                        requireContext().showToast("Push 설정이 저장되었습니다")
                    }
                }
            }
            SettingsPreference().setFirstLoginCheck(false)
        }
        getFirebaseToken()

        pageAdapter = TimeLinePagingAdapter(requireActivity())
        pageAdapter.apply {
            onClickTimelineItem = object : TimeLinePagingAdapter.TimeLineClickListener{
                override fun onFavoriteClicked(timeline: Timeline, position: Int) {
                    storyViewModel.likeStoryInTimeLine(Likes(mainViewModel.user.value!!.seq, timeline.story.seq))
                    changeLikes(position)
                }

                override fun onScrapClicked(timeline: Timeline, position: Int) {
                    storyViewModel.scrapStory(Scrap(mainViewModel.user.value!!.seq, timeline.story.seq))
                    changeScrap(position)
                }
                override fun onReplyClicked(timeline: Timeline) {
                    findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToStoryDetailFragment(timeline.story.seq))
                }
                override fun onMenuClicked(view: View, timeline: Timeline) {
                    menuSelectedTimeline = timeline
                    if(timeline.profile.userSeq == mainViewModel.user.value!!.seq){
                        PopupMenu(requireContext(), view).apply {
                            setOnMenuItemClickListener(this@TimeLineFragment)
                            inflate(R.menu.menu_story_own_writer)
                            if (mainViewModel.checkFollowUser(timeline.story.writerSeq)) {
                                menu[0].title = "언팔로우"
                            } else {
                                menu[0].title = "팔로우"
                            }
                            show()
                        }
                    }else {
                        PopupMenu(requireContext(), view).apply {
                            setOnMenuItemClickListener(this@TimeLineFragment)
                            inflate(R.menu.menu_story_visiters)
                            if (mainViewModel.checkFollowUser(timeline.story.writerSeq)) {
                                menu[0].title = "언팔로우"
                            } else {
                                menu[0].title = "팔로우"
                            }
                            show()
                        }
                    }
                }
                override fun onProfileClicked(timeline: Timeline) {
                    if(timeline.profile.userSeq != mainViewModel.user.value!!.seq)
                        findNavController().safeNavigate(TimeLineFragmentDirections.actionTimeLineFragmentToUserProfileFragment(timeline.profile.userSeq))
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
                        .actionTimeLineFragmentToStoryRegisterFragment()
                )
            }

            recyclerTimeline.apply {
                adapter = pageAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            ivFilter.setOnClickListener {
                TagSelectOneFragment(requireContext(), FLAG_TIMELINE).let{
                    it.show(parentFragmentManager, null)
                    it.onDismissDialogListener = object : TagSelectOneFragment.OnDismissDialogListener{
                        override fun onDismiss(tagName: String, flag: Int) {
                            when(flag){
                                JOB_TAG -> storyViewModel.getTimelineJobAndAllList(mainViewModel.user.value!!.seq, tagName)
                                INTEREST_TAG -> storyViewModel.getTimelineInterestAndAllList(mainViewModel.user.value!!.seq, tagName)
                                ALL_TAG -> storyViewModel.getAllTimelineListValue(mainViewModel.user.value!!.seq)
                            }
                        }
                    }
                }
            }
        }

    }

    private fun initViewModel(){
        storyViewModel.getAllTimelineListValue(mainViewModel.user.value!!.seq)
        storyViewModel.pagingAllTimelineList.observe(viewLifecycleOwner){
            Log.d(TAG, "onViewCreated: $it")
            pageAdapter.submitData(this.lifecycle, it)
        }
        storyViewModel.pagingTimelineJobAndAllList.observe(viewLifecycleOwner){
            pageAdapter.submitData(this.lifecycle, it)
        }
        storyViewModel.pagingTimelineInterestAndAllList.observe(viewLifecycleOwner){
            pageAdapter.submitData(this.lifecycle, it)
        }
        storyViewModel.pagingTimelineFollowList.observe(viewLifecycleOwner){
            pageAdapter.submitData(this.lifecycle, it)
        }


        mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
        storyViewModel.isFollowSucceed.observe(viewLifecycleOwner){
            mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.follow -> {
                storyViewModel.doFollow(Follow(mainViewModel.user.value!!.seq, menuSelectedTimeline.story.writerSeq))
                if (mainViewModel.checkFollowUser(menuSelectedTimeline.story.writerSeq)) {
                    item.title = "언팔로우"
                }else{
                    item.title = "팔로우"
                }
            }
            R.id.story_delete ->{
                storyViewModel.deleteStory(menuSelectedTimeline.story)
            }
            R.id.block -> {
                storyViewModel.blockStory(mainViewModel.user.value!!.seq, menuSelectedTimeline.story)
            }
        }
        return true
    }


    private fun getFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {
            if (!it.isSuccessful) {
                Log.d(TAG, "onCreate: FCM 토큰 얻기 실패", it.exception)
                return@OnCompleteListener
            }else{
                Log.d(TAG, "getFirebaseToken: ${it.result}")
                FCMPreference().setFcmToken(it.result)
            }
            settingViewModel.updateFCMToken(mainViewModel.user.value!!)

        })
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotiChannel("sduty_id", "sduty")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotiChannel(channelId: String, channelName: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)

        val notificationManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}
