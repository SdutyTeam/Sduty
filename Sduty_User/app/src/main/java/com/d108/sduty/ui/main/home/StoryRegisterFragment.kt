package com.d108.sduty.ui.main.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentStoryRegisterBinding
import com.d108.sduty.utils.showToast

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }
    }

    // 팝업 메뉴 클릭 이벤트 설정
    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.publicDisclosure -> {
                disclosure = 0
                requireContext().showToast("전체 공개 클릭 : " + disclosure)
                true
            }
            R.id.followerDisclosure -> {
                disclosure = 1
                requireContext().showToast("팔로워만 공개 클릭 : " + disclosure)
                true
            }
            R.id.privateDisclosure -> {
                disclosure = 2
                requireContext().showToast("나만 보기 클릭 : " + disclosure)
                true
            }
            else -> false
        }
    }
}
