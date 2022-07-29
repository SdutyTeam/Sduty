package com.d108.sduty.ui.main.home

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.common.KAKAO_JOIN
import com.d108.sduty.databinding.FragmentStoryRegisterBinding
import com.d108.sduty.ui.sign.LoginFragmentDirections
import com.d108.sduty.utils.navigateBack
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showToast
import com.github.dhaval2404.imagepicker.ImagePicker

//게시물 등록 - 글 내용입력, 이미지 추가/ 미리보기, 카메라 or 이미지 선택, 태그 선택
private const val TAG ="StoryRegisterFragment"
class StoryRegisterFragment : Fragment(), PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: FragmentStoryRegisterBinding
    // (공개 범위) 0 : 전체 공개, 1 : 팔로워만, 2 : 나만 보기
    private var disclosure = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryRegisterBinding.inflate(inflater, container, false)
        binding.apply {
            etWrite.addTextChangedListener(object : TextWatcher {
                // 입력이 끝날 때 동작
                override fun afterTextChanged(p0: Editable?) {}
                // 입력하기 전에 동작
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                // 타이핑되는 텍스트에 변화가 있으면 동작
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvWordLength.text = "${etWrite.length()} / 200"
                    if (etWrite.length() > 200) {
                        //requireContext().showToast("최대 200자까지 입력 가능합니다.")
                        etWrite.setTextColor(Color.RED)
                        tvWordLength.setTextColor(Color.RED)
                    }
                    else {
                        etWrite.setTextColor(Color.BLACK)
                        tvWordLength.setTextColor(Color.BLACK)
                    }
                }
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startForProfileImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result : ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == Activity.RESULT_OK) {
                    // Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    // mProfileUri = fileUri
                    binding.apply {
                        //imgStory.setImageURI(fileUri)
                        imgStory.visibility = View.VISIBLE
                        btnAddImg.visibility = View.GONE
                        val fileUriStr = fileUri.toString()
                        findNavController().safeNavigate(
                            StoryRegisterFragmentDirections
                                .actionStoryRegisterFragmentToStoryDecoFragment(fileUriStr)
                        )
                    }
                } else if (resultCode == ImagePicker.RESULT_ERROR) {
                    requireContext().showToast(ImagePicker.getError(data))
                } else {
                    requireContext().showToast("Task Cancelled")
                }
            }

        binding.apply {
            // 공개 범위 설정 버튼 클릭 시, 팝업 메뉴 보이기
            btnDisclosure.setOnClickListener {
                PopupMenu(requireContext(), it).apply {
                    // implements OnMenuItemClickListener
                    setOnMenuItemClickListener(this@StoryRegisterFragment)
                    inflate(R.menu.disclosure_menu)
                    show()
                }
            }
            btnAddImg.setOnClickListener {
                ImagePicker.with(requireActivity())
                    .crop(3f, 4f)	    //Crop image and let user choose aspect ratio.
                    .compress(1024)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
            }
            imgStory.setOnClickListener {
                ImagePicker.with(requireActivity())
                    .crop(3f, 4f)	    //Crop image and let user choose aspect ratio.
                    .compress(1024)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
            }
            ivBack.setOnClickListener {
                navigateBack(requireActivity())
            }
        }
    }

    // 팝업 메뉴 클릭 이벤트 설정
    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.privateDisclosure -> {
                disclosure = 0
                requireContext().showToast("나만 보기 클릭 : " + disclosure)
                true
            }
            R.id.followerDisclosure -> {
                disclosure = 1
                requireContext().showToast("팔로워만 공개 클릭 : " + disclosure)
                true
            }
            R.id.publicDisclosure -> {
                disclosure = 2
                requireContext().showToast("전체 공개 클릭 : " + disclosure)
                true
            }
            else -> false
        }
    }
}
