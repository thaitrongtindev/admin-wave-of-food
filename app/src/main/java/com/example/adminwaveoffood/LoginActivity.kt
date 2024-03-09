package com.example.adminwaveoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnLogin.setOnClickListener() {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvDontHaveAccount.setOnClickListener() {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}