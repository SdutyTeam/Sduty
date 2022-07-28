package com.d108.sduty.ui.main.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.d108.sduty.databinding.FragmentStoryDecoBinding
import com.d108.sduty.utils.showToast

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fileUriStr = args.fileUriStr
        if (fileUriStr.equals("")) {
            requireContext().showToast("값이 비어 있습니다!!")
        }
        else {
            binding.apply {
                imgPreview.setImageURI(Uri.parse(fileUriStr))
            }
        }
    }
}
