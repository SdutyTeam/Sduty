package com.d108.sduty.ui.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d108.sduty.R
import com.d108.sduty.adapter.TaskSpinnerAdapter
import com.d108.sduty.databinding.FragmentStoryDecoBinding
import com.d108.sduty.model.dto.Task
import com.d108.sduty.ui.main.timer.viewmodel.TimerViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.showToast

//게시물 사진 꾸미기 - 타임스탬프, 텍스트 컬러, 템플릿 선택, 공유, 저장
private const val TAG ="StoryDecoFragment"
class StoryDecoFragment(var mContext: Context, var fileUriStr: String) : DialogFragment() {
    private lateinit var binding: FragmentStoryDecoBinding
    private val timerViewModel: TimerViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var spinnerAdapter: TaskSpinnerAdapter

    private var taskList = listOf<Task>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel.displayBottomNav(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDecoBinding.inflate(inflater, container, false)
        val fileUriStr = fileUriStr
        if (fileUriStr.isEmpty()) {
            requireContext().showToast("값이 비어 있습니다!!")
        }
        else {
            binding.apply {
                imgPreview.setImageURI(Uri.parse(fileUriStr))
            }
        }
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            // 템플릿 미적용
            btnDecoNone.setOnClickListener {
                val layoutParams = imgPreview.layoutParams as FrameLayout.LayoutParams
                layoutParams.setMargins(0, 0, 0, 0)
                imgPreview.layoutParams = layoutParams
                tvTimeGradient.visibility = View.INVISIBLE
                tvTime.visibility = View.INVISIBLE
            }
            // 기본 템플릿 적용
            btnDecoBasic.setOnClickListener {
                tvTime.visibility = View.VISIBLE
                tvTimeGradient.visibility = View.INVISIBLE
            }
            btnDecoSduty.setOnClickListener {
                tvTime.visibility = View.INVISIBLE
                tvTimeGradient.visibility = View.VISIBLE
            }
            var startX = 0f
            var startY = 0f
            tvTime.setOnTouchListener { v, event ->
                when(event.action){
                    MotionEvent.ACTION_DOWN ->{
                        startX = event.x
                        startY = event.y
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val movedX: Float = event.x - startX
                        val movedY: Float = event.y - startY
                        v.x = v.x + movedX
                        v.y = v.y + movedY
                    }
                }
                true
            }
            tvTimeGradient.setOnTouchListener { v, event ->
                when(event.action){
                    MotionEvent.ACTION_DOWN ->{
                        startX = event.x
                        startY = event.y
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val movedX: Float = event.x - startX
                        val movedY: Float = event.y - startY
                        v.x = v.x + movedX
                        v.y = v.y + movedY
                    }
                }
                true
            }

            // 이미지를 Reg로
            ivDoneDeco.setOnClickListener {
                // Bitmap : to hold the pixels where the canvas will be drawn.
                val bitmap = Bitmap.createBitmap(layoutPreview.width, layoutPreview.height, Bitmap.Config.ARGB_8888)
                // Construct a canvas with the specified bitmap to draw into.
                val canvas = Canvas(bitmap)
                // Drawing commands — to indicate to the canvas what to draw.
                layoutPreview.draw(canvas)
                saveImageBitmap(bitmap)
            }
        }
        initViewModel()
    }

    private fun initViewModel(){
        timerViewModel.apply {
            getTodayReport(mainViewModel.user.value!!.seq)
            report.observe(viewLifecycleOwner){
                taskList = it.tasks
                spinnerAdapter = TaskSpinnerAdapter(requireContext(), android.R.layout.simple_list_item_1, it.tasks)
                binding.spinnerStoryDeco.apply {
                    adapter = spinnerAdapter
                    this.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            Log.d(TAG, "onItemSelected: ${timerViewModel.report.value}")
                            binding.tvTime.text = "${timerViewModel.report.value?.date} ${taskList[p2].startTime.substring(0, 5)} ~ ${taskList[p2].endTime.substring(0, 5)}"
                            binding.tvTimeGradient.text = "${timerViewModel.report.value?.date} ${taskList[p2].startTime.substring(0, 5)} ~ ${taskList[p2].endTime.substring(0, 5)}"
                        }
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }
                }
            }
        }
    }


    private fun saveImageBitmap(bitmap: Bitmap) {
        //findNavController().safeNavigate(StoryDecoFragmentDirections.actionStoryDecoFragmentToStoryRegisterFragment(bitmap))
        onSaveBtnClickListener.onClick(bitmap)
        dismiss()
    }
    lateinit var onSaveBtnClickListener: OnSaveBtnClickListener
    interface OnSaveBtnClickListener{
        fun onClick(bitmap: Bitmap)
    }


    override fun onResume() {
        super.onResume()
        resizeDialog()
    }

    private fun resizeDialog() {
        val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        val deviceHeight = size.y
        params?.width = (deviceWidth * 1).toInt()
        params?.height = (deviceHeight * 1).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
