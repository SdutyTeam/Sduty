package com.d108.sduty.ui.main.home

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.IdRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.StoryAdapter
import com.d108.sduty.common.FLAG_FOLLOWEE
import com.d108.sduty.common.FLAG_FOLLOWER
import com.d108.sduty.common.NOT_PROFILE
import com.d108.sduty.databinding.FragmentUserProfileBinding
import com.d108.sduty.model.dto.Follow
import com.d108.sduty.model.dto.InterestHashtag
import com.d108.sduty.model.dto.JobHashtag
import com.d108.sduty.model.dto.Story
import com.d108.sduty.ui.main.mypage.MyPageFragmentDirections
import com.d108.sduty.ui.sign.dialog.TagSelectDialog
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showToast

//사용자 프로필 - 사용자 닉네임, 프로필 사진, 숫자 표시(게시물, 팔로우, 팔로워), 한줄소개,
// 잔디그래프,게시물(그리드+스크롤) , 더 보기, 업적
private const val TAG = "UserProfileFragment"
class UserProfileFragment : Fragment(), PopupMenu.OnMenuItemClickListener   {
    private lateinit var binding: FragmentUserProfileBinding
    private val viewModel: StoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val args: UserProfileFragmentArgs by navArgs()
    private lateinit var storyAdapter: StoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getUserStoryListValue(args.userSeq)
        viewModel.userStoryList.observe(viewLifecycleOwner){
            storyAdapter.list = it
        }
        mainViewModel.profile.observe(viewLifecycleOwner){
            binding.apply {
                vm = viewModel
                mainVM = mainViewModel
                Log.d(TAG, "initViewModel: story: ${it}")
            }
        }
        viewModel.profile.observe(viewLifecycleOwner){
            binding.apply {
                vm = viewModel
                mainVM = mainViewModel
                Log.d(TAG, "initViewModel: main: ${it}")
            }
        }
        viewModel.isFollowSucceed.observe(viewLifecycleOwner){
            mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
            viewModel.getProfileValue(args.userSeq)
        }
    }

    private fun initView() {
        storyAdapter = StoryAdapter(requireActivity())
        storyAdapter.onClickStoryListener = object : StoryAdapter.OnClickStoryListener{
            override fun onClick(story: Story, position: Int) {
                findNavController().safeNavigate(UserProfileFragmentDirections.actionUserProfileFragmentToStoryDetailFragment(story.seq))
            }
        }
        binding.apply {
            vm = viewModel
            mainVM = mainViewModel
            recylerStory.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = storyAdapter
            }
            btnFollow.setOnClickListener {
                viewModel.doFollow(Follow(mainViewModel.user.value!!.seq, viewModel.profile.value!!.userSeq))
                mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
            }

            ivSetting.setOnClickListener {
                PopupMenu(requireContext(), it).apply {
                    setOnMenuItemClickListener(this@UserProfileFragment)
                    inflate(R.menu.menu_story_visiters)
                    if (mainViewModel.checkFollowUser(viewModel.profile.value!!.userSeq)) {
                        menu[0].title = "언팔로우"
                    }else{
                        menu[0].title = "팔로우"
                    }
                    show()
                }
            }
            tvCountFollow.setOnClickListener {
                findNavController().safeNavigate(UserProfileFragmentDirections.actionUserProfileFragmentToFollowFragment(args.userSeq, FLAG_FOLLOWER))
            }
            tvCountFollower.setOnClickListener {
                findNavController().safeNavigate(UserProfileFragmentDirections.actionUserProfileFragmentToFollowFragment(args.userSeq, FLAG_FOLLOWEE))
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.follow -> {
                viewModel.doFollow(Follow(mainViewModel.user.value!!.seq, viewModel.profile.value!!.userSeq))
                mainViewModel.getProfileValue(mainViewModel.user.value!!.seq)
                if (mainViewModel.checkFollowUser(viewModel.profile.value!!.userSeq)) {
                    item.title = "언팔로우"
                }else{
                    item.title = "팔로우"
                }
            }
        }
        return true
    }
}
