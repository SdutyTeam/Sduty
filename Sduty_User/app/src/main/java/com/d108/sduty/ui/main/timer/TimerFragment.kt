package com.d108.sduty.ui.main.timer

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentTermsBinding
import com.d108.sduty.databinding.FragmentTimerBinding
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TimerFragment"
class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding

    private val mainViewModel : MainViewModel by activityViewModels()
    private val timerViewModel : TimerViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 첫 화면을 설정한다.
        initView()

        binding.apply {
            // 날짜 선택
            commonSelectedDate.setOnClickListener {
                showDatePicker()
            }
        }


    }

    // 화면 초기화
    private fun initView(){
        // 오늘 날짜로 변경
        val curDate = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale("ko", "KR"))
        val today = simpleDateFormat.format(curDate)

        binding.commonSelectedDate.text = today
        //timerViewModel.selectDate(today)
    }

    private fun showDatePicker(){
        val cal = Calendar.getInstance()
        // 날짜 선택 후 동작할 리스너
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            val selectedDate = "${year}년 ${month + 1}월 ${day}일"
            binding.commonSelectedDate.text = selectedDate
            //timerViewModel.selectDate(selectedDate)
        }
        // 다이얼로그 출력
        DatePickerDialog(requireActivity(), dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun initViewModel(){

    }
}
