package com.example.adminwaveoffood

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivityAddItemBinding
import com.example.adminwaveoffood.model.AllMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngredient: String
    private var imageUri: Uri?  = null
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_item)

        //initialize authenticated
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.btnBack.setOnClickListener {
            finish()
        }


        /// on click btn add Item
        binding.btnAddItem.setOnClickListener {
            // get data from edit text
            foodName = binding.editItemName.text.toString().trim()
            foodPrice = binding.editItemPrice.text.toString().trim()
            foodDescription = binding.edtDescripton.text.toString().trim()
            foodIngredient = binding.edtIngredients.text.toString().trim()
            if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredient.isBlank())) {
                uploadData()
                Toast.makeText(this, "Upload Data Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Upload Data Success", Toast.LENGTH_SHORT).show()

            }
        }


        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.imageAdd.setImageURI(uri)
                imageUri = uri
            }
        }

        binding.imageAdd.setOnClickListener {
                pickImage.launch("image/*")
            }
        }
    private fun uploadData() {
        // create a reference to "menu" in node database

        val menuRef =database.getReference("menu")
        // generate a unique key for the new menu item
        val newItemKey = menuRef.push().key
        if (imageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_storage/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(imageUri!!)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadImage ->
                    // create a new menu item
                    val newItem = AllMenu(
                        foodName =  foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredient = foodIngredient,
                        foodImage = downloadImage.toString()
//                        foodImage = imageUri.toString()
                    )
                    newItemKey?.let {
                        key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Data upload success", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Data upload failure ", Toast.LENGTH_SHORT).show()

                        }
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Image upload failure ", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    }




    // Kiểm tra xem quyền đọc bộ nhớ đã được cấp chưa

    // Hàm để yêu cầu quyền đọc bộ nhớ


// Trong onCreate hoặc nơi phù hợp khác

    // Xử lý kết quả yêu cầu quyền







