package com.d108.sduty.ui.main.timer

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.StudyListAdapter
import com.d108.sduty.databinding.FragmentReportBinding
import com.d108.sduty.model.dto.Report
import com.d108.sduty.model.dto.Task
import com.d108.sduty.ui.main.study.MyStudyFragmentDirections
import com.d108.sduty.ui.main.timer.adapter.TaskListAdapter
import com.d108.sduty.ui.main.timer.dialog.TaskDialog
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.convertTimeDateToString
import com.d108.sduty.utils.safeNavigate
import com.google.firebase.database.collection.LLRBNode
import java.util.*

private const val TAG = "ReportFragment"

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding

    private val mainViewModel: MainViewModel by activityViewModels()
    private val timerViewModel: TimerViewModel by viewModels({ requireActivity() })

    private lateinit var today: String

    private lateinit var taskList: List<Task>
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뷰모델 초기화
        initViewModel()
        Log.d(TAG, "onViewCreated: ")
        // 화면 초기화
        initView()
        // 해야할 일 갱신
        updateAdapter(timerViewModel.report.value!!)

    }

    private fun initViewModel() {
        timerViewModel.report.observe(viewLifecycleOwner) { report ->
            updateAdapter(report)
            updatePlanner(report)
        }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        // 날짜 선택 후 동작할 리스너
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            val selectedDate = "${year}년 ${month + 1}월 ${day}일"
            binding.commonSelectedDate.text = selectedDate
            timerViewModel.selectDate(selectedDate)

            when (selectedDate != today) {
                true -> { // 오늘
                    binding.apply {
                        btnReturnToday.text = "오늘($today) 로 돌아가기"
                        btnReturnToday.visibility = View.VISIBLE
                    }
                }
                false -> { // 다른 날
                    binding.apply {
                        btnReturnToday.visibility = View.GONE
                    }
                }
            }
        }
        // 캘린더 다이얼로그 출력
        DatePickerDialog(
            requireActivity(), dateSetListener, cal.get(Calendar.YEAR), cal.get(
                Calendar.MONTH
            ), cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun initView() {
        today = convertTimeDateToString(Date(System.currentTimeMillis()), "yyyy년 M월 d일")

        binding.apply {
            // 날짜 선택
            commonSelectedDate.setOnClickListener {
                showDatePicker()
            }

            commonShare.setOnClickListener {
                TaskDialog().apply {
                    arguments = Bundle().apply {
                        putString("Action", "CustomAdd")
                    }
                    show(this@ReportFragment.requireActivity().supportFragmentManager, "TaskDialog")
                }
            }

            fabTimer.setOnClickListener {
                findNavController().safeNavigate(ReportFragmentDirections.actionReportFragmentToTimerFragment())
            }
        }

        // 첫 화면은 오늘 날짜로 설정
        binding.commonSelectedDate.text = today
        timerViewModel.selectDate(today)
    }

    // 리사이클러뷰 갱신
    private fun updateAdapter(report: Report) {
        taskListAdapter = TaskListAdapter(requireActivity(), report.tasks)
        taskListAdapter.onClickTaskItem = object : TaskListAdapter.OnClickTaskItem {
            override fun onClick(view: View, fragmentActivity: FragmentActivity, position: Int) {

                TaskDialog().apply {
                    arguments = Bundle().apply {
                        putString("Action", "Info")
                        putInt("Position", position)
                    }
                    show(fragmentActivity.supportFragmentManager, "TaskDialog")
                }
            }
        }
        binding.rvReport.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskListAdapter
        }
    }

    private fun updatePlanner(report: Report) {
        binding.tlPlanner.removeAllViews()

        for (i in 1..24){
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT)

            val tv = TextView(requireContext())
            tv.layoutParams = TableRow.LayoutParams(60,60)
            tv.text = i.toString()
            Log.d(TAG, "updatePlanner: $i")
            tableRow.addView(tv)

            for (j in 1..6) {
                val view = View(requireContext())
                view.layoutParams = TableRow.LayoutParams(60, 60)

                when (j % 2) {
                    0 -> view.setBackgroundResource(R.color.app_purple)
                    1 -> view.setBackgroundResource(R.color.app_blue)
                }
                tableRow.addView(view)
            }

            binding.tlPlanner.addView(tableRow)
        }


    }

}