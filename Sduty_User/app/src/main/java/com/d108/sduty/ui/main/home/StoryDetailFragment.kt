package com.d108.sduty.ui.main.home

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.annotation.IdRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.ReplyAdapter
import com.d108.sduty.common.NOT_PROFILE
import com.d108.sduty.databinding.FragmentStoryDetailBinding
import com.d108.sduty.model.dto.*
import com.d108.sduty.ui.main.study.StudyDetailFragmentDirections
import com.d108.sduty.ui.sign.dialog.TagSelectDialog
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.navigateBack
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showToast
import com.google.android.material.navigation.NavigationView

// 게시글 상세 - 게시글 사진, 더보기, 좋아요, 댓글 등록, 조회, 스크랩
private const val TAG ="StoryDetailFragment"
class StoryDetailFragment : Fragment(), PopupMenu.OnMenuItemClickListener  {
    private lateinit var binding: FragmentStoryDetailBinding
    private val viewModel: StoryViewModel by viewModels()
    private val args: StoryDetailFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var replyAdapter: ReplyAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val displaymetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels
        val deviceHeight = deviceWidth / 3 * 4
        binding.apply {
            ivTimelineContent.layoutParams.width = deviceWidth
            ivTimelineContent.layoutParams.height = deviceHeight
            lifecycleOwner = this@StoryDetailFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel.apply {
            getTimelineValue(args.seq, mainViewModel.user.value!!.seq)
            timeLine.observe(viewLifecycleOwner){
                replyAdapter.list = timeLine.value!!.replies
                binding.vm = viewModel
                Log.d(TAG, "initViewModel: $it")
            }
            replyList.observe(viewLifecycleOwner){
                replyAdapter.list = it
            }
        }
        mainViewModel.apply {
            getProfileValue(mainViewModel.user.value!!.seq)
        }

    }

    private fun initView(){
        replyAdapter = ReplyAdapter()
        binding.apply {
            vm = viewModel
            tvRegister.setOnClickListener {
                if(etReply.text.isEmpty()){
                    requireContext().showToast("내용을 입력해 주세요")
                    return@setOnClickListener
                }
                viewModel.insertReply(Reply(args.seq, mainViewModel.user.value!!.seq, etReply.text.toString()))
                etReply.setText("")
            }
            recyclerReply.apply {
                adapter = replyAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            }
            ivFavorite.setOnClickListener {
                viewModel.likeStory(Likes(mainViewModel.user.value!!.seq, args.seq), mainViewModel.user.value!!.seq)
            }
            ivScrap.setOnClickListener {
                viewModel.scrapStory(Scrap(mainViewModel.user.value!!.seq, args.seq))
            }
            ivMenu.setOnClickListener {
                @IdRes var menuId = 0
                if(viewModel.timeLine.value!!.story.writerSeq == mainViewModel.user.value!!.seq){
                    menuId = R.menu.menu_story_own_writer
                }else{
                    menuId = R.menu.menu_story_visiters
                }
                PopupMenu(requireContext(), it).apply {
                    setOnMenuItemClickListener(this@StoryDetailFragment)
                    inflate(menuId)
                    show()
                }
            }
            commonTopBack.setOnClickListener {
                navigateBack(requireActivity())
            }
            constProfileTop.setOnClickListener {
                if(mainViewModel.user.value!!.seq == viewModel.timeLine.value!!.story.writerSeq)
                    findNavController().safeNavigate(StoryDetailFragmentDirections.actionStoryDetailFragmentToMyPageFragment())
                else{
                    findNavController().safeNavigate(StoryDetailFragmentDirections.actionStoryDetailFragmentToUserProfileFragment(viewModel.timeLine.value!!.story.writerSeq))
                }
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.story_delete -> {
                requireActivity().showAlertDialog(
                    "삭제",
                    "스토리를 삭제하시겠습니까?",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            viewModel.deleteStory(args.seq)
                            requireContext().showToast("삭제 되었습니다.")
                            findNavController().popBackStack()
                        }
                    })
            }
            R.id.story_update_tag -> {
                TagSelectDialog(requireContext()).let {
                    it.arguments = bundleOf("flag" to NOT_PROFILE)
                    it.onClickConfirm = object : TagSelectDialog.OnClickConfirm {
                        override fun onClick(
                            selectedJobList: JobHashtag?,
                            selectedInterestList: MutableList<InterestHashtag>
                        ) {
                            var story = viewModel.timeLine.value!!.story
                            var list = mutableListOf<Int>()
                            for (item in selectedInterestList) {
                                list.add(item.seq)
                            }
                            story.interestHashtag = list
                            viewModel.updateStory(story)
                        }
                    }
                    it.show(parentFragmentManager, null)
                }
            }
            R.id.follow -> {
                viewModel.doFollow(
                    Follow(mainViewModel.user.value!!.seq, viewModel.timeLine.value!!.story.writerSeq))
                if (mainViewModel.checkFollowUser(viewModel.timeLine.value!!.story.writerSeq)) {
                    item.title = "언팔로우"
                }else{
                    item.title = "팔로우"
                }
            }
        }

        return true
    }
}
