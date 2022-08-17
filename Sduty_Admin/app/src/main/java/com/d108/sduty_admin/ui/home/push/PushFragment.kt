package com.d108.sduty_admin.ui.home.push

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.d108.sduty_admin.R
import com.d108.sduty_admin.databinding.FragmentPushBinding

private const val TAG = "PushFragment"
class PushFragment : Fragment() {
    private lateinit var binding: FragmentPushBinding
    private val viewModel: PushViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPushBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView(){
        binding.apply {
            btnSend.setOnClickListener {
                if(etNotice.text.isNotEmpty()){
                    val builder = AlertDialog.Builder(requireContext())
                    builder.apply {
                        setTitle("푸시 메시지")
                        setMessage("정말로 보내시겠습니까?\n모두에게 전송됩니다.")
                        setPositiveButton("발송"){ dialog, _ ->
                            viewModel.sendFCM(etNotice.text.toString())
                            Toast.makeText(context, "전송 되었습니다", Toast.LENGTH_SHORT).show()
                            etNotice.setText("")
                        }
                        setNegativeButton("취소", null)
                    }.show()
                }
            }
        }
    }
}
