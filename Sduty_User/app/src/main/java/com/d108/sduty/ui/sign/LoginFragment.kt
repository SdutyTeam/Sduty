package com.d108.sduty.ui.sign

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d108.sduty.R
import com.d108.sduty.databinding.FragmentLoginBinding
import com.d108.sduty.ui.MainActivity
import com.d108.sduty.ui.camstudy.preview.PreviewFragment
import com.d108.sduty.utils.safeNavigate
import com.d108.sduty.utils.showAlertDialog
import com.d108.sduty.utils.showToast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

//첫화면 - 로그인 / ID, PW 입력, 로그인 , 카카오, 네이버 로그인, 아이디/비밀번호 찾기, 회원가입 하기
private const val TAG ="LoginFragment"
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val signViewModel: SignViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        checkPermission()
    }


    private fun initViewModel(){
        signViewModel.user.observe(viewLifecycleOwner){
            requireContext().showToast("${it.name}님 반갑습니다.")
        }
        signViewModel.isLoginSucceed.observe(viewLifecycleOwner){
            when(it){
                true ->{
//                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTimeLineFragment())
                    parentFragmentManager.beginTransaction().replace(R.id.frame_main, PreviewFragment()).commit()
                }
                false -> requireContext().showToast("아이디와 비밀번호를 확인해 주세요")
            }
        }
        signViewModel.isExistKakaoAccount.observe(viewLifecycleOwner){
            when(it){
                false -> {
                    val listener = DialogInterface.OnClickListener { _, _ ->
                        signViewModel.kakaoJoin()
                    }
                    requireActivity().showAlertDialog("", "가입되지 않은 계정입니다. 가입 하시겠습니까?", listener)
                }
            }
        }
        signViewModel.isExistNaverAccount.observe(viewLifecycleOwner){
            when(it){
                false -> {
                    val listener = DialogInterface.OnClickListener { _, _ ->
                        signViewModel.naverJoin()
                    }
                    requireActivity().showAlertDialog("", "가입되지 않은 계정입니다. 가입 하시겠습니까?", listener)
                }
            }
        }
        signViewModel.isJoinSucced.observe(viewLifecycleOwner){
            when(it) {
                true -> requireContext().showToast("가입이 완료되었습니다. 로그인해 주세요")
            }
        }
    }

    private fun initView() {
        binding.apply {
            btnLogin.setOnClickListener {
                val id = binding.etId.text.toString()
                val pw = binding.etPw.text.toString()
                if(id.isEmpty() || pw.isEmpty()){

                }else{
                    signViewModel.login(id, pw)
                }
            }
            btnKakaoLogin.setOnClickListener {
                kakaoLogin()
            }
            btnNaverLogin.setOnClickListener {
                naverLogin()
            }
            btnJoin.setOnClickListener {
                findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToJoinFragment())
            }
            btnSendSms.setOnClickListener{
//                signViewModel.sendOTP("01037449555")
                signViewModel.sendOTP("01049177914")
            }
            btnAuthCode.setOnClickListener{
                signViewModel.checkOTP(etAuthCode.text.toString())
            }

        }
    }

    private fun naverLogin() {
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                signViewModel.naverLogin(NaverIdLoginSDK.getAccessToken()!!)
                signViewModel.setAccessToken(NaverIdLoginSDK.getAccessToken()!!)
            }
            override fun onFailure(httpStatus: Int, message: String) {
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NaverIdLoginSDK.authenticate(requireContext(), oauthLoginCallback)
    }


    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                signViewModel.kakaoLogin(token.accessToken)
                signViewModel.setAccessToken(token.accessToken)
            }
        }
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                } else if (token != null) {
                    signViewModel.kakaoLogin(token.accessToken)
                    signViewModel.setAccessToken(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun checkPermission(){
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {

            }
            override fun onPermissionDenied(deniedPermissions: List<String>) {
                requireActivity().showToast("모든 권한을 허용해야 이용이 가능합니다.")
                requireActivity().finish()
            }

        }
        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }
}
