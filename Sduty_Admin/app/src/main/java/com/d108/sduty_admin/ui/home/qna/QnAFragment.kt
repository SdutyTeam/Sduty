package com.d108.sduty_admin.ui.home.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty_admin.adapter.QnaAdapter
import com.d108.sduty_admin.databinding.FragmentQnaBinding
import com.d108.sduty_admin.model.dto.Qna
import com.d108.sduty_admin.ui.MainViewModel

private const val TAG ="QnAFragment"
class QnAFragment : Fragment() {
    private lateinit var binding: FragmentQnaBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var qnaAdapter: QnaAdapter
    private val viewModel: QnaViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQnaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        qnaAdapter = QnaAdapter()
        qnaAdapter.onClickQnaListener = object : QnaAdapter.OnClickQnaListener{
            override fun onClick(qna: Qna) {
                QuestionDetailDialog(qna).let {
                    it.onClickQuestionDetailListener = object : QuestionDetailDialog.OnClickQuestionDetailListener{
                        override fun onClick(qna: Qna) {
                            qna.ansWriter = mainViewModel.admin.value!!.name
                            viewModel.updateQna(qna)
                        }
                    }
                    it.show(parentFragmentManager, null)
                }
            }
        }
        binding.apply {
            recyclerQna.apply {
                adapter = qnaAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

        viewModel.apply {
            qnaList.observe(viewLifecycleOwner){
                qnaAdapter.list = it
            }
            getQna()
        }
    }
}
