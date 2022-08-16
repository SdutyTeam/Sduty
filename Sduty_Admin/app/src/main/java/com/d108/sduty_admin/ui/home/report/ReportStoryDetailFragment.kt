package com.d108.sduty_admin.ui.home.report

import android.app.ProgressDialog.show
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty_admin.adapter.ReplyAdapter
import com.d108.sduty_admin.databinding.FragmentReportStoryDetailBinding
import com.d108.sduty_admin.model.dto.Reply
import com.d108.sduty_admin.ui.MainViewModel
import com.d108.sduty_admin.ui.home.report.dialog.ConfirmDialog

private const val TAG ="ReportStoryDetailFragment"
class ReportStoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentReportStoryDetailBinding
    private val args: ReportStoryDetailFragmentArgs by navArgs()
    private val viewModel: StoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var replyAdapter: ReplyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportStoryDetailBinding.inflate(layoutInflater, container, false)
        setStoryImageSize()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        replyAdapter = ReplyAdapter()
        replyAdapter.onClickReplyListener = object : ReplyAdapter.OnClickReplyListener{
            override fun onClick(reply: Reply) {
                ConfirmDialog().let {
                    it.onClickConfirmListener = object : ConfirmDialog.OnClickConfirmListener{
                        override fun onClick() {
                            viewModel.deleteReply(reply.seq)
                        }
                    }
                    it.show(parentFragmentManager, null)
                }
            }
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@ReportStoryDetailFragment
            recyclerReply.apply {
                adapter = replyAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            }
            ivMenu.setOnClickListener {
                ConfirmDialog().let {
                    it.onClickConfirmListener = object : ConfirmDialog.OnClickConfirmListener{
                        override fun onClick() {
                            viewModel.deleteStory(viewModel.timeLine.value!!.story.seq)
                            findNavController().popBackStack()
                        }
                    }
                    it.show(parentFragmentManager, null)
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            getTimelineValue(args.storySeq)
            replyList.observe(viewLifecycleOwner){
                replyAdapter.list = it
            }
        }
    }


    private fun setStoryImageSize() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val displaymetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceWidth = displaymetrics.widthPixels
        val deviceHeight = deviceWidth / 3 * 4
        binding.apply {
            ivTimelineContent.layoutParams.width = deviceWidth
            ivTimelineContent.layoutParams.height = deviceHeight
            lifecycleOwner = this@ReportStoryDetailFragment
        }
    }
}
