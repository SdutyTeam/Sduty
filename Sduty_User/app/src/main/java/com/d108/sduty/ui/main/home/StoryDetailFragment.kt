package com.d108.sduty.ui.main.home

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.adapter.ReplyAdapter
import com.d108.sduty.databinding.FragmentStoryDetailBinding
import com.d108.sduty.model.dto.Reply
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.ui.viewmodel.StoryViewModel
import com.d108.sduty.utils.showToast

// 게시글 상세 - 게시글 사진, 더보기, 좋아요, 댓글 등록, 조회, 스크랩
private const val TAG ="StoryDetailFragment"
class StoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentStoryDetailBinding
    private val viewModel: StoryViewModel by viewModels()
    private val args: StoryDetailFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var replyAdapter: ReplyAdapter

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
            getTimelineValue(args.seq)
            timeLine.observe(viewLifecycleOwner){
                replyAdapter.list = timeLine.value!!.replies
            }
            replyList.observe(viewLifecycleOwner){
                replyAdapter.list = it
            }

        }
    }

    private fun initView(){
        replyAdapter = ReplyAdapter()
        binding.apply {
            vm = viewModel
            ivRegisterReply.setOnClickListener {
                if(etReply.text.isEmpty()){
                    requireContext().showToast("내용을 입력해 주세요")
                    return@setOnClickListener
                }
                viewModel.insertReply(Reply(args.seq, mainViewModel.user.value!!.seq, etReply.text.toString()))
            }
            recyclerReply.apply {
                adapter = replyAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            }

        }
    }

}
