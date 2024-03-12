package com.example.adminwaveoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.adminwaveoffood.databinding.ActivitySignUpBinding
import com.example.adminwaveoffood.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var nameOfRestaurant: String
    private lateinit var email: String
    private lateinit var username: String
    private lateinit var database: DatabaseReference
    private lateinit var password: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //initialize firebase auth
        auth = Firebase.auth
        // initialize firebase database
        database = Firebase.database.reference

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)


        val locationList = arrayListOf<String>(
            "HCM", "HN", "DN", "BT"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.chooseLocation
        autoCompleteTextView.setAdapter(adapter)

        binding.btnCreateUser.setOnClickListener {
            //get text from edit text
            username = binding.edtName.text.toString().trim()
            nameOfRestaurant = binding.edtNameRestaurant.text.toString().trim()
            email = binding.edtEmailOfPhoneNumber.text.toString().trim()
            password = binding.edtPassword.text.toString().trim()

            // check input
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(nameOfRestaurant) || TextUtils.isEmpty(
                    email
                ) || TextUtils.isEmpty(password)
            ) {
                Toast.makeText(this, "Please filled all details", Toast.LENGTH_SHORT).show()
            } else {
                createUserWithEmail(email, password)
            }

        }

        binding.tvAlreadyHaveAnAcount.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun createUserWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()  {
            task -> if (task.isSuccessful) {
                saveUser()
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
        } else {
            Toast.makeText(this, "Account created Failed", Toast.LENGTH_SHORT).show()

        }
        }
    }

    private fun saveUser() {
        //get text from edit text
        username = binding.edtName.text.toString().trim()
        nameOfRestaurant = binding.edtNameRestaurant.text.toString().trim()
        email = binding.edtEmailOfPhoneNumber.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()

        val user = UserModel(username, nameOfRestaurant, email, password)
        val userId = auth.currentUser?.uid
        //save dataa of user
        database.child("user").child(userId.toString()).setValue(user)
    }
}