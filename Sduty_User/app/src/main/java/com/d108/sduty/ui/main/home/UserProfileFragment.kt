package com.d108.sduty.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.d108.sduty.adapter.StoryAdapter
import com.d108.sduty.databinding.FragmentUserProfileBinding
import com.d108.sduty.model.dto.Story
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.safeNavigate

//사용자 프로필 - 사용자 닉네임, 프로필 사진, 숫자 표시(게시물, 팔로우, 팔로워), 한줄소개,
// 잔디그래프,게시물(그리드+스크롤) , 더 보기, 업적
private const val TAG = "UserProfileFragment"
class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private val viewModel: StoryViewModel by viewModels()
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
        viewModel.getProfileValue(args.userSeq)
        viewModel.userStoryList.observe(viewLifecycleOwner){
            storyAdapter.list = it
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
            recylerStory.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = storyAdapter
            }
        }
    }
}
