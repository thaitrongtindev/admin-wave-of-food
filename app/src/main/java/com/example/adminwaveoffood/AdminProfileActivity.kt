package com.example.adminwaveoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_profile)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}