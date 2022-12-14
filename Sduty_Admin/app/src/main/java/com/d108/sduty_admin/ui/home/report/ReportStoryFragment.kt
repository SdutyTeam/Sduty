package com.d108.sduty_admin.ui.home.report

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.d108.sduty_admin.R
import com.d108.sduty_admin.adapter.StoryPagingAdapter
import com.d108.sduty_admin.databinding.FragmentReportStoryBinding
import com.d108.sduty_admin.model.dto.Story
import com.d108.sduty_admin.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG ="ReportStoryFragment"
class ReportStoryFragment : Fragment() {
    private lateinit var binding: FragmentReportStoryBinding
    private val viewModel: StoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var storyAdapter: StoryPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.setVisibilityBottomNav(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView(){
        storyAdapter = StoryPagingAdapter(requireActivity())
        storyAdapter.onClickStoryListener = object : StoryPagingAdapter.OnClickStoryListener{
            override fun onClick(story: Story) {
                findNavController().navigate(ReportStoryFragmentDirections.actionReportStoryFragmentToReportStoryDetailFragment(story.seq))
            }
        }
        binding.apply {
            recyclerStory.apply {
                adapter = storyAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)
            }
            commonTopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initViewModel(){
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getPagingReportStory().collectLatest {
                storyAdapter.submitData(it)
            }
        }
        viewModel.apply {
            getJobListValue()
        }
    }
}
