package com.d108.sduty_admin.ui.home.push

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d108.sduty_admin.R
import com.d108.sduty_admin.databinding.FragmentPushBinding

private const val TAG = "PushFragment"
class PushFragment : Fragment() {
    private lateinit var binding: FragmentPushBinding
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
    }
}
