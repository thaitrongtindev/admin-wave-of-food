package com.example.adminwaveoffood

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private  val PERMISSION_REQUEST_CODE = 100



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_item)

        binding.btnBack.setOnClickListener {
            finish()
        }
        if (!isReadStoragePermissionGranted()) {
            requestStoragePermission()
        }

        val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.imageAdd.setImageURI(uri)
            }
        }

        binding.imageAdd.setOnClickListener {
            if (isReadStoragePermissionGranted()) {
                pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                requestStoragePermission()
            }
        }

    }



    val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        uri ->
        if (uri != null) {
            binding.imageAdd.setImageURI(uri)
        }
    }

    // Kiểm tra xem quyền đọc bộ nhớ đã được cấp chưa
    fun isReadStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Hàm để yêu cầu quyền đọc bộ nhớ
    fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

// Trong onCreate hoặc nơi phù hợp khác

    // Xử lý kết quả yêu cầu quyền
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, có thể thực hiện hành động chọn hình ảnh
                pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                // Quyền bị từ chối, có thể hiển thị thông báo hoặc thực hiện hành động khác ở đây

                Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }





