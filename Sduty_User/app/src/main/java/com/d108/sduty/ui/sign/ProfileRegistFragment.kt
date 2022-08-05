package com.d108.sduty.ui.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.common.PROFILE
import com.d108.sduty.databinding.FragmentProfileRegistBinding
import com.d108.sduty.model.dto.InterestHashtag
import com.d108.sduty.model.dto.JobHashtag
import com.d108.sduty.model.dto.Profile
import com.d108.sduty.ui.sign.dialog.TagSelectDialog
import com.d108.sduty.ui.sign.viewmodel.ProfileViewModel
import com.d108.sduty.ui.viewmodel.MainViewModel
import com.d108.sduty.utils.DateFormatUtil
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
    private var jobHashtag: JobHashtag? = null
    private var interestHashtagList = mutableListOf<InterestHashtag>()
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
        imageUrl = ""
        initView()
        initViewModel()
    }

    private fun initViewModel(){
        viewModel.profile.observe(viewLifecycleOwner){
            mainViewModel.setProfileValue(it)
            findNavController().navigate(ProfileRegistFragmentDirections.actionProfileRegistFragmentToTimeLineFragment())
        }
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
            tvInterest.setOnClickListener{
                openTagSelectDialog()
            }
            tvJob.setOnClickListener {
                openTagSelectDialog()
            }
        }
    }

    private fun openTagSelectDialog() {
        TagSelectDialog(requireContext()).let {
            it.arguments = bundleOf("flag" to PROFILE)
            it.onClickConfirm = object : TagSelectDialog.OnClickConfirm{
                override fun onClick(selectedJobList: JobHashtag?, selectedInterestList: MutableList<InterestHashtag>) {

                }
            }
            it.show(parentFragmentManager, null)
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

    private fun saveProfile(){
        binding.apply{
            val nickname = etNickname.text.toString()

            val publicJob = viewModel.flagJob.value!!

            val publicInterest = viewModel.flagInterest.value!!
            val birthInput = etBirth.text.toString()
            val publicBirth = viewModel.flagBirth.value!!
            val introduce = etIntroduce.text.toString()
            var msg = ""
            var birth = DateFormatUtil.converYYYYMMDD(birthInput)
            Log.d(TAG, "saveProfile: ${birth}")
            if(birthInput.isEmpty()){
                msg = "생년월일을 정확히 입력해 주세요"
            }else if(nickname.isEmpty()){
                msg = "별명을 입력해 주세요"
            }
            else if(introduce.isEmpty()){
                msg = "자기소개를 입력해 주세요"
            }
            else if(imageUrl.isEmpty()){
                msg = "이미지를 선택해 주세요"
            }
            else if(birth == null) {
                msg = "생년월일을 정확히 입력해 주세요"
            }

            if(!msg.isEmpty()){
                requireContext().showToast(msg)
                return

            }else{
                //viewModel.insertProfile(Profile(mainViewModel.user.value!!.seq, nickname, birth!!, publicBirth, introduce, "", jobHashtag?.seq.toString(), publicJob, interestHashtagList, publicInterest, 1), imageUrl)
            }

        }
    }
}
