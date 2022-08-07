package com.d108.sduty.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.d108.sduty.R
import com.d108.sduty.adapter.FollowAdapter
import com.d108.sduty.common.FLAG_FOLLOWER
import com.d108.sduty.databinding.FragmentFollowBinding
import com.d108.sduty.ui.main.mypage.setting.viewmodel.FollowViewModel
import com.google.android.material.tabs.TabLayout

private const val TAG ="FollowFragment"
class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val viewModel: FollowViewModel by viewModels()
    private val args: FollowFragmentArgs by navArgs()
    private lateinit var followAdapter: FollowAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initViewModel(){
        viewModel.apply {
            if(args.flag == FLAG_FOLLOWER){
                binding.tabFollow.selectTab(binding.tabFollow.getTabAt(0))
                getFollower(args.userSeq)
            }else{
                binding.tabFollow.selectTab(binding.tabFollow.getTabAt(1))
                getFollowee(args.userSeq)
            }
            followerList.observe(viewLifecycleOwner){
                followAdapter.list = followerList.value!!
            }
            followeeList.observe(viewLifecycleOwner){
                followAdapter.list = followeeList.value!!
            }
        }
    }

    private fun initView(){
        followAdapter = FollowAdapter(args.userSeq)
        binding.apply {
            tabFollow.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        0 -> {
                            viewModel.getFollower(args.userSeq)
                        }
                        1 -> {
                            viewModel.getFollowee(args.userSeq)
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        0 -> {
                            viewModel.getFollower(args.userSeq)
                        }
                        1 -> {
                            viewModel.getFollowee(args.userSeq)
                        }
                    }
                }
            })
            recyclerFollow.apply {
                adapter = followAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}