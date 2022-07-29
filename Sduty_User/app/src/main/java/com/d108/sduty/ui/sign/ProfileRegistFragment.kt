package com.d108.sduty.ui.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d108.sduty.databinding.FragmentProfileRegistBinding
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.ui.sign.viewmodel.ProfileViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.UriPathUtil
import com.d108.sduty.utils.showToast
import java.util.*

//프로필 등록 - 프로필 사진, 별명, 직업, 관심 분야, 생년월일, 자기소개
private const val TAG = "ProfileRegistFragment"
class ProfileRegistFragment : Fragment() {
    private lateinit var binding: FragmentProfileRegistBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var imageUrl: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initViewModel(){

    }

    private fun initView(){
        binding.apply {
            lifecycleOwner = this@ProfileRegistFragment
            vm = viewModel
            btnSave.setOnClickListener {
                saveProfile()
            }
            etNickname.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.checkNickname(binding.etNickname.text.toString())
                }
            })
            ivProfile.setOnClickListener {
                loadProfileImage()
            }
        }
    }

    private fun saveProfile(){
        binding.apply{
            val nickname = etNickname.text.toString()
            val job = etJob.text.toString()
            val publicJob = viewModel.flagJob.value!!
            val interest = etInterest.text.toString()
            val publicInterest = viewModel.flagInterest.value!!
            val birth = Date()
            val publicBirth = viewModel.flagBirth.value!!
            val introduce = etIntroduce.text.toString()
            if(nickname.isEmpty() || job.isEmpty() || interest.isEmpty() || birth == null || introduce.isEmpty()){
                requireContext().showToast("모든 항목을 입력해 주세요")
            }else{
                viewModel.insertProfile(Profile(nickname, birth, publicBirth, introduce, "", job, publicJob, interest, publicInterest, 1), imageUrl)
            }
        }
    }

    private fun loadProfileImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        resultLauncher.launch(intent);
    }
    val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imageUri = it.data!!.data!!
                imageUrl = UriPathUtil().getPath(requireContext(), imageUri).toString()
                binding.ivProfile.setImageURI(imageUri)
            }
        }
}
