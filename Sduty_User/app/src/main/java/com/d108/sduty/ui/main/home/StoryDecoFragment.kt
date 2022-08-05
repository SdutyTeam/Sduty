package com.d108.sduty.ui.main.home

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d108.sduty.databinding.FragmentStoryDecoBinding
import com.d108.sduty.ui.main.home.viewmodel.HomeViewModel
import com.d108.sduty.utils.navigateBack
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showToast
import java.util.*

//게시물 사진 꾸미기 - 타임스탬프, 텍스트 컬러, 템플릿 선택, 공유, 저장
private const val TAG ="StoryDecoFragment"
class StoryDecoFragment : Fragment() {
    private lateinit var binding: FragmentStoryDecoBinding
    private val args: StoryDecoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDecoBinding.inflate(inflater, container, false)
        val fileUriStr = args.fileUriStr
        if (fileUriStr.equals("")) {
            requireContext().showToast("값이 비어 있습니다!!")
        }
        else {
            binding.apply {
                imgPreview.setImageURI(Uri.parse(fileUriStr))
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // 템플릿 미적용
            btnDecoNone.setOnClickListener {
                val layoutParams = imgPreview.layoutParams as FrameLayout.LayoutParams
                layoutParams.setMargins(0, 0, 0, 0)
                imgPreview.layoutParams = layoutParams

                tvTime.visibility = View.INVISIBLE
            }
            // 기본 템플릿 적용
            btnDecoBasic.setOnClickListener {
                tvTime.visibility = View.VISIBLE
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
    }

    private fun saveImageBitmap(bitmap: Bitmap) {
        findNavController().safeNavigate(StoryDecoFragmentDirections.actionStoryDecoFragmentToStoryRegisterFragment(bitmap))
    }
}
