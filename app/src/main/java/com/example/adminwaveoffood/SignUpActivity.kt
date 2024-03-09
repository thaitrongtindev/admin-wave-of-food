package com.example.adminwaveoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        val locationList = arrayListOf<String>(
            "HCM", "HN", "DN", "BT"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.chooseLocation
        autoCompleteTextView.setAdapter(adapter)

        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.tvAlreadyHaveAnAcount.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}