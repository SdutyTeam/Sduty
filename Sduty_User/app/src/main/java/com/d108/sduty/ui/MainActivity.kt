package com.d108.sduty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d108.sduty.R
import com.d108.sduty.databinding.ActivityMainBinding
import com.d108.sduty.ui.sign.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frame_main, LoginFragment()).commit()
    }
}