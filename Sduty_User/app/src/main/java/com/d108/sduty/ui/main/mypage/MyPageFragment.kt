package com.d108.sduty.ui.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.d108.sduty.adapter.ContributionAdapter
import com.d108.sduty.adapter.StoryAdapter
import com.d108.sduty.databinding.FragmentMyPageBinding
import com.d108.sduty.model.dto.Story
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.safeNavigate

// 마이페이지 - 내 닉네임, 프로필 사진, 숫자 표시(게시물, 팔로우, 팔로워), 한줄소개, 프로필 편집, 통계, 잔디그래프, 탭(내 게시물/ 스크랩), 내 게시물(그리드+스크롤) , 설정, 업적
private const val TAG ="MyPageFragment"
class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val viewModel: StoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var contributionAdapter: ContributionAdapter
    private lateinit var storyAdapter: StoryAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()

        val list = mutableListOf<Boolean>()
        for(i in 0..181){
            list.add(Math.random() < 0.5)
        }

        contributionAdapter.list = list
    }

    private fun initViewModel() {
        viewModel.userStoryList.observe(viewLifecycleOwner){
            storyAdapter.list = it
        }
        viewModel.getUserStoryListValue(mainViewModel.user.value!!.seq)
    }

    private fun initView(){
        contributionAdapter = ContributionAdapter()
        storyAdapter = StoryAdapter(requireActivity())
        binding.apply {
            lifecycleOwner = this@MyPageFragment
            vm = mainViewModel
            recylerStory.apply {
                adapter = storyAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)
            }
            recylerContribution.apply {
                adapter = contributionAdapter
                layoutManager = GridLayoutManager(requireContext(), 26)
            }
            ivAchieve.setOnClickListener {
                findNavController().safeNavigate(MyPageFragmentDirections.actionMyPageFragmentToAchievementFragment())
            }
            ivSetting.setOnClickListener {
                findNavController().safeNavigate(MyPageFragmentDirections.actionMyPageFragmentToSettingFragment())
            }
        }
    }
}
